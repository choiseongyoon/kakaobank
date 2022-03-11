package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReceiveMoneyIn {
    @NotNull(message = "수취할 수 없는 거래입니다.")
    private Long tId;
    @NotBlank(message = "계좌번호를 입력해주세요.")
    private String rAccId;

    public ReceiveMoneyIn() {
    }

    public ReceiveMoneyIn(Long tId, String rAccId) {
        this.tId = tId;
        this.rAccId = rAccId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getrAccId() {
        return rAccId;
    }

    public void setrAccId(String rAccId) {
        this.rAccId = rAccId;
    }
}
