package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.CancelTar;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.CancelTarMapper;
import com.kakaobank.daina.assignment.mapper.SimTransDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeploymentService {

    private final SimTransDetailMapper simTransDetailMapper;
    private final CancelTarMapper cancelTarMapper;
    private final VerificationService verificationService;
    private final CancelService cancelService;
    private final CheckTimeService checkTimeService;

    private final Logger logger = LoggerFactory.getLogger(DeploymentService.class);

    public DeploymentService(CheckTimeService checkTimeService, CancelService cancelService, VerificationService verificationService, SimTransDetailMapper simTransDetailMapper, CancelTarMapper cancelTarMapper) {
        this.simTransDetailMapper = simTransDetailMapper;
        this.cancelTarMapper = cancelTarMapper;
        this.verificationService = verificationService;
        this.cancelService = cancelService;
        this.checkTimeService = checkTimeService;
    }

    
    @Scheduled(fixedRate = 1000*60)
    public void after23hours() {
        // 간편이체거래내역 날짜 초과된 경우
        List<SimTransDetail> simTransDetails = simTransDetailMapper.findOverDate();

        // 취소대상 속성 update - 거래번호로 찾아서, 취소대상테이블 insert
        for (int i=0; i<simTransDetails.size(); i++){
            SimTransDetail simTransDetail = simTransDetails.get(i);
            try {
                checkTimeService.cancelTarget(simTransDetail);
                // TODO: 2022-03-21 계좌 입력 요청 메시지 전송부
            }catch (BizException e){
                logger.error("에러발생해따!"+ i);
            }
        }
    }
    
    @Scheduled(fixedRate = 1000*60)
    // 복잡한 로직, 대량 처리하는 로직에서 예외발생하는 경우, 모두 롤백이 된다.
    public void after24hours() {
        // 취소대상테이블 날짜 경과된 경우 select
        List<CancelTar> cancelTars = cancelTarMapper.findOverDate();
        // 취소 검증부 호출
        for(int i=0; i<cancelTars.size(); i++){
            //각 거래 당 검증부 거치고 계좌 정상인 경우 이체 취소 호출하도록
            //업무 예외에 대해서는 중단없이 프로세스가 진행되도록 하기 위한 try-catch
            CancelTar cancelTar = cancelTars.get(i);
            try {
                checkTimeService.cancelProcess(cancelTar);
            }catch (BizException e){
                logger.error("에러발생해따!"+ i);
            }
        }

    }
}
