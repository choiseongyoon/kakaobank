package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;

public class CreateRnameIn {
    @NotBlank(message = "실명을 입력해주세요.")
    private String rName;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String rNick;
    @NotBlank(message = "카카오톡 고유번호를 입력해주세요.")
    private String reKkoUid;

    public CreateRnameIn() {
    }

    public CreateRnameIn(String rName, String rNick, String reKkoUid) {
        this.rName = rName;
        this.rNick = rNick;
        this.reKkoUid = reKkoUid;
    }

    public String getrNick() {
        return rNick;
    }

    public void setrNick(String rNick) {
        this.rNick = rNick;
    }

    public String getReKkoUid() {
        return reKkoUid;
    }

    public void setReKkoUid(String reKkoUid) {
        this.reKkoUid = reKkoUid;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}
