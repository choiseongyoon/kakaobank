package com.kakaobank.daina.assignment.dto;

public class CreateAccountIn {
    private String nickname;
    private String korName;
    private String telNum;

    public CreateAccountIn() {
    }

    public CreateAccountIn(String nickname, String korName, String telNum) {
        this.nickname = nickname;
        this.korName = korName;
        this.telNum = telNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
