package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.exception.PasswordCountException;
import com.kakaobank.daina.assignment.mapper.AccInfoMapper;
import com.kakaobank.daina.assignment.mapper.ReceiHisMapper;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class LoginService {

    private final AccInfoMapper accInfoMapper;

    public LoginService(AccInfoMapper accInfoMapper) {

        this.accInfoMapper = accInfoMapper;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
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

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public boolean verifyPassword(Long inputBaccPass, String baccId) {
        AccInfo accInfo = accInfoMapper.findBaccAll(baccId);
        if (accInfo == null) {
            throw new BizException("존재하지 않은 계좌번호입니다.");
        }

        return this.verifyPassword(inputBaccPass, accInfo);
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void verifyName(String username, String receivename) {
        if (!username.equals(receivename)) {
            // TODO: 2022-03-11 이체취소프로세스 추가

            throw new BizException("실명이 일치하지 않습니다.");
        }

    }


}
