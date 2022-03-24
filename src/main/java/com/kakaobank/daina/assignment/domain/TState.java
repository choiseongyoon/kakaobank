package com.kakaobank.daina.assignment.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class TState {
    private String tStateId;
    private Long serialNum;
    private Long tId;
    private LocalDate nDate;
    private LocalTime nTime;
    private String paDeCode;
    private String accSubCode;
    private Long tAmount;
    private String sSumup;

    public TState() {
    }

    public TState(String tStateId, Long serialNum, Long tId, LocalDate nDate, LocalTime nTime, String paDeCode, String accSubCode, Long tAmount, String sSumup) {
        this.tStateId = tStateId;
        this.serialNum = serialNum;
        this.tId = tId;
        this.nDate = nDate;
        this.nTime = nTime;
        this.paDeCode = paDeCode;
        this.accSubCode = accSubCode;
        this.tAmount = tAmount;
        this.sSumup = sSumup;
    }

    public String gettStateId() {
        return tStateId;
    }

    public void settStateId(String tStateId) {
        this.tStateId = tStateId;
    }

    public Long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Long serialNum) {
        this.serialNum = serialNum;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public LocalDate getnDate() {
        return nDate;
    }

    public void setnDate(LocalDate nDate) {
        this.nDate = nDate;
    }

    public LocalTime getnTime() {
        return nTime;
    }

    public void setnTime(LocalTime nTime) {
        this.nTime = nTime;
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

    public Long gettAmount() {
        return tAmount;
    }

    public void settAmount(Long tAmount) {
        this.tAmount = tAmount;
    }

    public String getsSumup() {
        return sSumup;
    }

    public void setsSumup(String sSumup) {
        this.sSumup = sSumup;
    }
    public static TState createNew(String tStateId, Long serialNum, Long tId, LocalDate nDate, LocalTime nTime, String paDeCode, String accSubCode, Long tAmount, String sSumup) {
        TState tState = new TState(
                tStateId,
                serialNum,
                tId,
                nDate,
                nTime,
                paDeCode,
                accSubCode,
                tAmount,
                sSumup
        );

        return tState;
    }
}
