package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.*;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.exception.PasswordCountException;
import com.kakaobank.daina.assignment.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class CancelService {

    SimTransDetailMapper simTransDetailMapper;
    CancelTarMapper cancelTarMapper;
    VerificationService verificationService;
    AccountingService accountingService;
    HistorySimTransDetailMapper historySimTransDetailMapper;
    AccInfoMapper accInfoMapper;

    public CancelService(AccInfoMapper accInfoMapper, HistorySimTransDetailMapper historySimTransDetailMapper, AccountingService accountingService, VerificationService verificationService, SimTransDetailMapper simTransDetailMapper, CancelTarMapper cancelTarMapper) {
        this.simTransDetailMapper = simTransDetailMapper;
        this.cancelTarMapper = cancelTarMapper;
        this.verificationService =  verificationService;
        this.accountingService = accountingService;
        this.historySimTransDetailMapper = historySimTransDetailMapper;
        this.accInfoMapper = accInfoMapper;
    }
    public List<SimTransDetail> findTransactions(String reKkoUid) {
        List<SimTransDetail> simTransDetails = simTransDetailMapper.findSend(reKkoUid);

        return simTransDetails;
    }
    public SimTransDetail findInformation(Long tId) {
        SimTransDetail simTransDetail = simTransDetailMapper.findById(tId);

        return simTransDetail;
    }

    @Transactional
    public void cancelMoney(Long tId, String tCode) {
        SimTransDetail byId = simTransDetailMapper.findById(tId);

        //거래존재여부, 이체구분코드 확인
        boolean checkCode  = verificationService.checkCode(byId, "C1");

        //이체 취소 기록_간편이체거래내역_update
        byId.editTcode(tCode);
        simTransDetailMapper.updatetCode(byId);

        //계좌 상태 검증
        AccInfo account = accInfoMapper.findBaccAll(byId.getAccId());
        if(account == null){
            throw new BizException("계좌정보가 존재하지 않습니다.");
        }
        if(!(account.getBaccStatus().equals("NORMAL") | account.getBaccStatus().equals("normal"))){
            throw new BizException("거래가 불가능한 계좌입니다.");
        }

        //잔액 업데이트
        account.editMoney(account.getBaccBalance()+byId.gettAmount());
        accInfoMapper.update(account);

        //취소대상인지 select해서 취소대상이면, 환급여부 업데이트
        verificationService.checkCancelCode(byId, "취소");

        //거래내역 저장 호출
        byId.editTcode(tCode);
        simTransDetailMapper.updatetCode(byId);

        //상세내역 insert
        HistorySimTransDetail historySimTransDetail = HistorySimTransDetail.createNew(byId.gettId(),
                byId.getAccId(),
                byId.getCtmId(),
                byId.getrName(),
                byId.getAccId(),
                byId.getReKkoUid(),
                byId.gettAmount(),
                byId.getCommission(),
                LocalDate.now(),
                LocalTime.now(),
                byId.gettCode());
        historySimTransDetailMapper.insert(historySimTransDetail);

        //회계처리호출
        accountingService.accountingTransfer(byId, historySimTransDetail, tCode);
    }
}
