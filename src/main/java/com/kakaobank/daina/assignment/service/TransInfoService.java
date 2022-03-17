package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.*;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.AccInfoMapper;
import com.kakaobank.daina.assignment.mapper.HistorySimTransDetailMapper;
import com.kakaobank.daina.assignment.mapper.ReceiHisMapper;
import com.kakaobank.daina.assignment.mapper.SimTransDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransInfoService {

    private final SimTransDetailMapper simTransDetailMapper;
    private final AccInfoMapper accInfoMapper;
    private final ReceiHisMapper receiHisMapper;
    private final HistorySimTransDetailMapper historySimTransDetailMapper;
    private final VerificationService verificationService;
    private final AccountingService accountingService;

    private final Logger logger = LoggerFactory.getLogger(TransInfoService.class);


    public TransInfoService(AccountingService accountingService, VerificationService verificationService, SimTransDetailMapper simTransDetailMapper, AccInfoMapper accInfoMapper, ReceiHisMapper receiHisMapper, HistorySimTransDetailMapper historySimTransDetailMapper) {
        this.accInfoMapper = accInfoMapper;
        this.simTransDetailMapper = simTransDetailMapper;
        this.receiHisMapper = receiHisMapper;
        this.historySimTransDetailMapper = historySimTransDetailMapper;
        this.verificationService = verificationService;
        this.accountingService = accountingService;
    }

    public AccInfo findTransactions(String bacc_id) {
        AccInfo accInfo = accInfoMapper.findBaccAll(bacc_id);

        return accInfo;
    }

    public List<SimTransDetail> findTransactions2(Long t_id) {
        List<SimTransDetail> accInfo = simTransDetailMapper.findTransAll(t_id);

        return accInfo;
    }


    //보내기 서비스 한 트랙잭션
    @Transactional
    public void sendMoney(SendMoneyIn sendMoneyIn) {


        SimTransDetail byId = simTransDetailMapper.findById(sendMoneyIn.gettId());

        //거래존재여부, 이체구분코드 확인
        boolean checkCode  = verificationService.checkCode(byId, "C0");

        //계좌 상태 검증, 잔액 검증
        AccInfo account = accInfoMapper.findBaccAll(byId.getAccId());

        if(account == null) {
            throw new BizException("계좌가 존재하지 않습니다.");
        }
        if(!(account.getBaccStatus().equals("NORMAL") | account.getBaccStatus().equals("normal"))){
            throw new BizException("거래가 불가능한 계좌입니다.");
        }

        if(sendMoneyIn.gettAmount()>account.getBaccBalance()){
            throw new BizException("잔액 한도를 확인해주세요.");
        }

        //비밀번호 검증
        boolean check = verificationService.verifyPassword(sendMoneyIn.getBaccPass(), account);
        if(check==false){
            throw new BizException("비밀번호가 일치하지 않습니다.");
        }

        //송금하기 (=잔액 업데이트)
        account.editMoney(account.getBaccBalance()-sendMoneyIn.gettAmount());
        accInfoMapper.update(account);

        //내역 업데이트
        updateTransfer(sendMoneyIn, byId);

        //이체이력이 있는 친구 리스트 업데이트
        //내역업데이트 후 진행해야함
        updateFriendsList(byId);

        //회계처리호출(보내기 C1)
        accountingService.accountingTransfer(byId, "C1");

    }

    private void updateTransfer(SendMoneyIn sendMoneyIn, SimTransDetail byId) {
        //간편이체거래내역 업데이트
        byId.editSend(sendMoneyIn.gettAmount());
        simTransDetailMapper.updatetCode(byId);

        //상세내역 insert
        historySimTransDetailMapper.insert(HistorySimTransDetail.createNew(byId.gettId(),
                byId.getAccId(),
                byId.getCtmId(),
                byId.getrName(),
                null,
                byId.getReKkoUid(),
                byId.gettAmount(),
                byId.getCommission(),
                byId.gettDate(),
                byId.gettTime(),
                byId.gettCode()));
    }

    // 이체이력이 있는 친구 리스트 업데이트
    private void updateFriendsList(SimTransDetail byId) {
        ReceiHis receiHis = receiHisMapper.findById(byId.getReKkoUid());
        if(receiHis == null) {
            //없으면 insert
            receiHisMapper.insert(ReceiHis.createNew(receiHis.getReKkoUid(), String.valueOf(receiHis.gettDate()), String.valueOf(receiHis.gettTime()), receiHis.getrName(), receiHis.getrNick()));
        }
        else {
            //있으면 update
            receiHis.edit(String.valueOf(byId.gettDate()), String.valueOf(byId.gettTime()));
            receiHisMapper.update(receiHis);
        }
    }
}
