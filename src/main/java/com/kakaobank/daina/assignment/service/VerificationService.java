package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.CancelTar;
import com.kakaobank.daina.assignment.domain.CtmBaccClose;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.exception.PasswordCountException;
import com.kakaobank.daina.assignment.mapper.AccInfoMapper;
import com.kakaobank.daina.assignment.mapper.CancelTarMapper;
import com.kakaobank.daina.assignment.mapper.CtmBaccCloseMapper;
import com.kakaobank.daina.assignment.mapper.SimTransDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class VerificationService {

    private final AccInfoMapper accInfoMapper;
    private final CancelTarMapper cancelTarMapper;
    private final SimTransDetailMapper simTransDetailMapper;
    private final CtmBaccCloseMapper ctmBaccCloseMapper;

    public static final Map<String, String> codeMessage =Map.of(
            "C0","비정상적인 거래입니다.",
            "C1","이체가 완료된 거래입니다.",
            "C2","이체가 취소된 거래입니다.",
            "C3","이미 처리된 거래입니다.",
            "C4","이체가 취소된 거래입니다.",
            "CX","비정상적인 거래입니다."
    );

    public VerificationService(CtmBaccCloseMapper ctmBaccCloseMapper, SimTransDetailMapper simTransDetailMapper, CancelTarMapper cancelTarMapper, AccInfoMapper accInfoMapper) {
        this.accInfoMapper = accInfoMapper;
        this.cancelTarMapper = cancelTarMapper;
        this.simTransDetailMapper = simTransDetailMapper;
        this.ctmBaccCloseMapper = ctmBaccCloseMapper;
    }

    //@Transactional(propagation= Propagation.REQUIRES_NEW)
    public boolean verifyPassword(Long sendBaccPass, AccInfo accInfo){

        //비밀번호 검증
        //0번은 5번 초과
        //1은 불일치
        //2는 일치
        if(accInfo.getCntVer()>=5) {
            accInfo.editCntVer(0L);
            accInfoMapper.updateCnt(accInfo);
            throw new PasswordCountException("비밀번호 확인 횟수를 초과했습니다.");
        }
        if(!sendBaccPass.equals(accInfo.getBaccPass())){
            //계좌 DB 업데이트
            accInfo.editCntVer(accInfo.getCntVer()+1L);
            accInfoMapper.updateCnt(accInfo);
            return false;
        } else {
            accInfo.editCntVer(0L);
            accInfoMapper.updateCnt(accInfo);
            return true;
        }

    }

    //@Transactional(propagation= Propagation.REQUIRES_NEW)
    public boolean verifyPassword(Long inputBaccPass, String baccId) {
        AccInfo accInfo = accInfoMapper.findBaccAll(baccId);
        if (accInfo == null) {
            throw new BizException("존재하지 않은 계좌번호입니다.");
        }

        return this.verifyPassword(inputBaccPass, accInfo);
    }

    @Transactional
    public boolean verifyName(String username, String receivename) {
        if (!username.equals(receivename)) {
            // TODO: 2022-03-11 이체취소프로세스 추가
            return false;
        }
        return true;
    }
    //거래존재여부, 이체구분코드 확인
    @Transactional
    public boolean checkCode(SimTransDetail simTransDetail, String matchingCode) {

        if(simTransDetail == null) {
            throw new BizException("간편이체내역이 존재하지 않습니다.");
        }

        String errorCode="";

        if(matchingCode.equals(simTransDetail.gettCode())){
            return true;
        }else if(simTransDetail.gettCode().equals("C0")){
            errorCode = "C0";
        }else if(simTransDetail.gettCode().equals("C1")){
            errorCode = "C1";
        }else if(simTransDetail.gettCode().equals("C2")){
            errorCode = "C2";
        }else if(simTransDetail.gettCode().equals("C3")){
            errorCode = "C3";
        }else if(simTransDetail.gettCode().equals("C4")){
            errorCode = "C4";
        }else             errorCode = "CX";

        throw new BizException(codeMessage.get(errorCode));

    }
    // TODO: 2022-03-17 취소 검증부
    @Transactional
    public boolean checkAccState(Long tId, String accId) {

        AccInfo accInfo = accInfoMapper.findBaccAll(accId);
        if (accInfo == null) {
            throw new BizException("거래정보가 존재하지 않습니다.");
        }
        if (accInfo.getBaccStatus().equals("normal")) {
            return true;
        }

        CancelTar cancelTar = cancelTarMapper.findById(tId);
        if (cancelTar == null) {
            throw new BizException("취소정보가 존재하지 않습니다.");
        }
        cancelTar.editRcode("E", "계좌정지");
        cancelTarMapper.updateRcode(cancelTar);

        SimTransDetail simTransDetail = simTransDetailMapper.findById(tId);
        if (simTransDetail == null) {
            throw new BizException("간편이체내역이 존재하지 않습니다.");
        }
        ctmBaccCloseMapper.insert(CtmBaccClose.createNew(simTransDetail, accInfo));

        return false;
    }

    //취소대상관리 여부 확인
    @Transactional
    public void checkCancelCode(SimTransDetail byId, String state){
        if(byId.getCancelCode().equals("Y")){
            //취소대상인 경우에 환급여부 수정해주기 update
            CancelTar cancelTar = cancelTarMapper.findById(byId.gettId());

            //코드가 Y거나 N이면 예외처리
            if (cancelTar.getrCode().equals("Y")){
                throw new BizException("이미 환급이 완료된 거래입니다.");
            }else if(cancelTar.getrCode().equals("E")){
                throw new BizException("이체 취소 대상 거래입니다.");
            }
            cancelTar.editRcode("Y", state + "완료");
            cancelTarMapper.updateRcode(cancelTar);
        }
    }


}
