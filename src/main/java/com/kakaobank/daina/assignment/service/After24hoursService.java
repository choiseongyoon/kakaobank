package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.CancelTar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class After24hoursService {
    private final VerificationService verificationService;
    private final CancelService cancelService;

    public After24hoursService(VerificationService verificationService, CancelService cancelService) {
        this.verificationService = verificationService;
        this.cancelService = cancelService;
    }
    @Transactional
    public void cancelProcess(CancelTar cancelTar){
        //CancelTar cancelTar = cancelTars.get(i);
        Boolean check = verificationService.checkAccState(cancelTar.gettId(), cancelTar.getAccId());
        if(check == true){
            // TODO: 2022-03-17 이체취소 호출
            cancelService.cancelMoney(cancelTar.gettId(), "C4");
        }

    }

}
