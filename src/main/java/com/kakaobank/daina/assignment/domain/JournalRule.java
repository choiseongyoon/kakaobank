package com.kakaobank.daina.assignment.domain;

public class JournalRule {
    private String caseNum;
    private Long  serialNum;
    private String paDeCode;
    private String accSubCode;

    public JournalRule() {
    }

    public JournalRule(String caseNum, Long serialNum, String paDeCode, String accSubCode) {
        this.caseNum = caseNum;
        this.serialNum = serialNum;
        this.paDeCode = paDeCode;
        this.accSubCode = accSubCode;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public Long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Long serialNum) {
        this.serialNum = serialNum;
    }

    public String getPaDeCode() {
        return paDeCode;
    }

    public void setPaDeCode(String paDeCode) {
        this.paDeCode = paDeCode;
    }

    public String getAccSubCode() {
        return accSubCode;
    }

    public void setAccSubCode(String accSubCode) {
        this.accSubCode = accSubCode;
    }
}
