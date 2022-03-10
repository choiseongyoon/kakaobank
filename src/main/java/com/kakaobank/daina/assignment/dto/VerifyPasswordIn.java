package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotNull;

public class VerifyPasswordIn {

    @NotNull(message = "비밀번호를 입력하세요")
    private Long baccPass;
    @NotNull(message = "계좌번호를 입력하세요")
    private String baccId;

    public VerifyPasswordIn() {
    }

    public VerifyPasswordIn(Long baccPass, String baccId) {
        this.baccPass = baccPass;
        this.baccId = baccId;
    }

    public String getBaccId() {
        return baccId;
    }

    public void setBaccId(String baccId) {
        this.baccId = baccId;
    }

    public Long getBaccPass() {
        return baccPass;
    }

    public void setBaccPass(Long baccPass) {
        this.baccPass = baccPass;
    }
}
