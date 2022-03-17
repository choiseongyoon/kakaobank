package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VerifyNameIn {

    @NotBlank(message = "사용자 정보가 없습니다.")
    private String username;
    @NotBlank(message = "거래 정보가 없습니다.")
    private String receivename;

    public VerifyNameIn() {
    }

    public VerifyNameIn(String username, String receivename) {
        this.username = username;
        this.receivename = receivename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
    }
}
