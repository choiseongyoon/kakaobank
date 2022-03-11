package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditAccountIn {
    @NotNull(message = "사용자아이디를 입력하세요")
    private Long accountId;
    @NotBlank(message = "한글이름을 입력하세요")
    private String korName;
    @NotBlank(message = "전화번호를 입력하세요")
    private String telNum;
    @NotBlank(message = "상태를 입력하세요")
    @Pattern(regexp = "NORMAL|DELETE", message = "유효한 상태 값이 아닙니다.")
    private String state;

    public EditAccountIn() {
    }

    public EditAccountIn(Long accountId, String korName, String telNum, String state) {
        this.accountId = accountId;
        this.korName = korName;
        this.telNum = telNum;
        this.state = state;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
