package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.*;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.AccInfoMapper;
import com.kakaobank.daina.assignment.mapper.HistorySimTransDetailMapper;
import com.kakaobank.daina.assignment.mapper.SendMapper;
import com.kakaobank.daina.assignment.mapper.SimTransDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class TransInfoService {

    private final SimTransDetailMapper simTransDetailMapper;
    private final AccInfoMapper accInfoMapper;
    private final SendMapper sendMapper;
    private final HistorySimTransDetailMapper historySimTransDetailMapper;

    public TransInfoService(SimTransDetailMapper transInfoMapper, AccInfoMapper accInfoMapper, SendMapper sendMapper, HistorySimTransDetailMapper historySimTransDetailMapper) {
        this.accInfoMapper = accInfoMapper;
        this.simTransDetailMapper = transInfoMapper;
        this.sendMapper = sendMapper;
        this.historySimTransDetailMapper = historySimTransDetailMapper;
    }

    public AccInfo findTransactions(String bacc_id) {
        AccInfo accInfo = accInfoMapper.findBaccAll(bacc_id);

        return accInfo;
    }

    public List<SimTransDetail> findTransactions2(String t_id) {
        List<SimTransDetail> accInfo = simTransDetailMapper.findTransAll(t_id);

        return accInfo;
    }


    @Transactional
    public void sendMoney(SendMoneyIn sendMoneyIn) {
        System.out.println(sendMoneyIn.gettAmount());


        // TODO: 2022-03-07 거래내역이 있는 지 확인, 거래내역상태확인
        SimTransDetail byId = simTransDetailMapper.findById(sendMoneyIn.gettId());

        if(byId == null) {
            throw new BizException("간편이체내역이 존재하지 않습니다.");
        }
        if(byId.gettCode().equals("C1")) {
            throw new BizException("간편이체가 완료된 거래입니다.");
        }else if(byId.gettCode().equals("C2")){
            throw new BizException("간편이체가 완료된 거래입니다.");
        }else if(byId.gettCode().equals("C3")){
            throw new BizException("간편이체가 취소된 거래입니다.");
        }else if(byId.gettCode().equals("C4")){
            throw new BizException("간편이체가 취소된 거래입니다.");
        }

        // TODO: 2022-03-07 계좌가 있는 지 확인, 계좌상태확인
        // TODO: 2022-03-07 잔액 검증
        AccInfo account = accInfoMapper.findBaccAll(byId.getAccId());

        if(account == null) {
            throw new BizException("계좌가 존재하지 않습니다.");
        }
        if(!account.getBaccStatus().equals("NORMAL")){
            throw new BizException("거래가 불가능한 계좌입니다.");
        }

        if(sendMoneyIn.gettAmount()>account.getBaccBalance()){
            throw new BizException("잔액 한도를 확인해주세요.");
        }

//        // TODO: 2022-03-07 비밀번호 검증
//        if(sendMoneyIn.getBaccPass() != account.getBaccPass()){
//            throw new BizException("비밀번호가 일치하지 않습니다.");
//        }

        // TODO: 2022-03-07 송금하기 (=잔액 업데이트)
        account.editMoney(account.getBaccBalance()-sendMoneyIn.gettAmount());
        accInfoMapper.update(account);

        // TODO: 2022-03-07 간편이체거래내역 업데이트
        byId.editTcode();
        simTransDetailMapper.updatetCode(byId);

        // 이체이력이 있는 친구 리스트 업데이트
        updateFriendsList(byId);

        // TODO: 2022-03-07 상세내역 insert
        historySimTransDetailMapper.insert(HistorySimTransDetail.createNew(byId.gettId(),
                byId.getAccId(), byId.getCtmId(), byId.getrName(), byId.getReKkoUid(), byId.gettAmount(), byId.getCommission(), byId.gettDate(), byId.gettTime(),
                byId.gettCode()));

    }

    private void updateFriendsList(SimTransDetail byId) {
        ReceiHis receiHis = sendMapper.findById(byId.getReKkoUid());
        if(receiHis == null) {
            //없으면 insert
            sendMapper.insert(ReceiHis.createNew(receiHis.getReKkoUid(), String.valueOf(receiHis.gettDate()), String.valueOf(receiHis.gettTime()), receiHis.getrName(), receiHis.getrNick()));
        }
        else {
            //있으면 update
            receiHis.edit(String.valueOf(receiHis.gettDate()), String.valueOf(receiHis.gettTime()));
            sendMapper.update(receiHis);
        }
    }
//    public void checkPassword(SendMoneyIn editAmountIn) {
//        if (editAmountIn.gettAmount() > editAmountIn.getBaccBalance()) {
//            throw new BizException("잔액 한도를 초과했습니다. 확인해주세요.");
//        }
//    }
//
//    public List<SimTransDetail> findSimTrans(String t_id) {
//        List<SimTransDetail> simInfo = simTransDetailMapper.findDetail(Long.parseLong(t_id));
//
//        return simInfo;
//    }

//    @Transactional
//    public HashMap editTcode(String t_id) {
//
//        SimTransDetail byId = simTransDetailMapper.findById(Long.parseLong(t_id));
//        if(byId == null) {
//            throw new BizException("존재하지 않는 사용자입니다.");
//        }
//
//        byId.editTcode();
//
//        simTransDetailMapper.updatetCode(byId);
//
//
//        return ob;
//    }

}
