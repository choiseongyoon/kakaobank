package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotNull;

public class ReceiveMoneyIn {

    @NotNull(message = "수취할 수 없는 거래입니다.")
    private Long tId;
    @NotNull(message = "실명을 입력해주세요.")
    private String inputrName;
    @NotNull(message = "계좌번호를 입력해주세요.")
    private String rAccId;

    public ReceiveMoneyIn() {
    }

    public ReceiveMoneyIn(Long tId, String inputrName, String rAccId) {
        this.tId = tId;
        this.inputrName = inputrName;
        this.rAccId = rAccId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getInputrName() {
        return inputrName;
    }

    public void setInputrName(String inputrName) {
        this.inputrName = inputrName;
    }

    public String getrAccId() {
        return rAccId;
    }

    public void setrAccId(String rAccId) {
        this.rAccId = rAccId;
    }
}
