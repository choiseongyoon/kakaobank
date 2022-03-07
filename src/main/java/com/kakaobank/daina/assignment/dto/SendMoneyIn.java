package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotNull;

public class SendMoneyIn {

    @NotNull(message = "송금금액을 입력하세요")
    private Long tAmount;
    @NotNull(message = "아이디를 입력하세요")
    private Long tId;
    @NotNull(message = "비밀번호를 입력하세요")
    private Long baccPass;

    public SendMoneyIn() {
    }

    public SendMoneyIn(Long tAmount, Long tId, Long baccPass) {
        this.tAmount = tAmount;
        this.tId = tId;
        this.baccPass = baccPass;
    }

    public Long gettAmount() {
        return tAmount;
    }

    public void settAmount(Long tAmount) {
        this.tAmount = tAmount;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getBaccPass() {
        return baccPass;
    }

    public void setBaccPass(Long baccPass) {
        this.baccPass = baccPass;
    }
}
