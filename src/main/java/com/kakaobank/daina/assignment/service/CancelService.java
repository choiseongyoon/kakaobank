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
import java.util.Map;

@Service
public class CancelService {

    SimTransDetailMapper simTransDetailMapper;
    CancelTarMapper cancelTarMapper;
    VerificationService verificationService;
    AccountingService accountingService;
    HistorySimTransDetailMapper historySimTransDetailMapper;

    public CancelService(HistorySimTransDetailMapper historySimTransDetailMapper, AccountingService accountingService, VerificationService verificationService, SimTransDetailMapper simTransDetailMapper, CancelTarMapper cancelTarMapper) {
        this.simTransDetailMapper = simTransDetailMapper;
        this.cancelTarMapper = cancelTarMapper;
        this.verificationService =  verificationService;
        this.accountingService = accountingService;
        this.historySimTransDetailMapper = historySimTransDetailMapper;
    }


    public void cancelMoney(Long tId, String tCode) {
        SimTransDetail byId = simTransDetailMapper.findById(tId);
        if(byId == null){
            throw new BizException("거래정보가 존재하지 않습니다.");
        }
        // TODO: 2022-03-17 이체 취소 기록_간편이체거래내역_update
        byId.editTcode(tCode);
        simTransDetailMapper.updatetCode(byId);

        // TODO: 2022-03-17 취소대상인지 select해서 취소대상이면, 환급여부 업데이트
        verificationService.checkCancelCode(byId, "취소");

        // TODO: 2022-03-17 거래내역 저장 호출
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

        // TODO: 2022-03-17 회계처리호출
        accountingService.accountingTransfer(byId, historySimTransDetail, tCode);
    }
}
