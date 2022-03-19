package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.*;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.PhantomReference;
import java.util.List;

@Service
public class AccountingService {
    private final JournalRuleMapper journalRuleMapper;
    private final TStateMapper tStateMapper;

    public AccountingService(JournalRuleMapper journalRuleMapper, TStateMapper tStateMapper) {
        this.journalRuleMapper = journalRuleMapper;
        this.tStateMapper = tStateMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(AccountingService.class);

    @Transactional
    public void accountingTransfer(SimTransDetail simTransDetail, HistorySimTransDetail historySimTransDetail, String caseNum) {
        //분개룰 확인
        List<JournalRule> journalRule = journalRuleMapper.findAll(caseNum);

        //거래전표번호 SELECT
        String tStateId = tStateMapper.getTstateId();
        String s = "간편이체거래_";

        for (int i = 0; i < journalRule.size(); i++) {
            JournalRule rule = journalRule.get(i);
            tStateMapper.insert(TState.createNew(tStateId, i+1L, simTransDetail.gettId(), historySimTransDetail.getnDate(), historySimTransDetail.getnTime(),
                    rule.getPaDeCode(), rule.getAccSubCode(), simTransDetail.gettAmount(), s.concat(caseNum)));
        }
    }
}
