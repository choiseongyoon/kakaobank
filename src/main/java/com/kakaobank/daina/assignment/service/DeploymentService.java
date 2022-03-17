package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.CancelTar;
import com.kakaobank.daina.assignment.domain.HistorySimTransDetail;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.ReceiveMoneyIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.AccInfoMapper;
import com.kakaobank.daina.assignment.mapper.CancelTarMapper;
import com.kakaobank.daina.assignment.mapper.HistorySimTransDetailMapper;
import com.kakaobank.daina.assignment.mapper.SimTransDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class DeploymentService {

    SimTransDetailMapper simTransDetailMapper;
    CancelTarMapper cancelTarMapper;
    private final Logger logger = LoggerFactory.getLogger(DeploymentService.class);

    public DeploymentService(SimTransDetailMapper simTransDetailMapper, CancelTarMapper cancelTarMapper) {
        this.simTransDetailMapper = simTransDetailMapper;
        this.cancelTarMapper = cancelTarMapper;
    }

    @Async
    @Scheduled(fixedRate = 1000*60)
    @Transactional
    public void after23hours() {
        // 간편이체거래내역 날짜 초과된 경우
        List<SimTransDetail> simTransDetails = simTransDetailMapper.findOverDate();

        // 취소대상 속성 update - 거래번호로 찾아서, 취소대상테이블 insert
        for (int i=0; i<simTransDetails.size(); i++){
            simTransDetailMapper.updateCancelCode(simTransDetails.get(i));
            cancelTarMapper.insert(CancelTar.createNew(simTransDetails.get(i)));
        }
    }

}
