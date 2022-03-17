package com.kakaobank.daina.assignment.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class SimTransDetail {
    private Long tId;
    private String accId;
    private String ctmId;
    private String ctmName;
    private String rNick;
    private String rName;
    private String reKkoUid;
    private Long tAmount;
    private Long commission;
    private LocalDate tDate;
    private LocalTime tTime;
    private LocalDate rDate;
    private LocalTime rTime;
    private String tCode;
    private String cancelCode;

    public SimTransDetail() {
    }

    public SimTransDetail(Long tId, String accId, String ctmId, String ctmName, String rNick, String rName, String reKkoUid, Long tAmount, Long commission, LocalDate tDate, LocalTime tTime, LocalDate rDate, LocalTime rTime, String tCode, String cancelCode) {
        this.tId = tId;
        this.accId = accId;
        this.ctmId = ctmId;
        this.ctmName = ctmName;
        this.rNick = rNick;
        this.rName = rName;
        this.reKkoUid = reKkoUid;
        this.tAmount = tAmount;
        this.commission = commission;
        this.tDate = tDate;
        this.tTime = tTime;
        this.rDate = rDate;
        this.rTime = rTime;
        this.tCode = tCode;
        this.cancelCode = cancelCode;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getCtmId() {
        return ctmId;
    }

    public void setCtmId(String ctmId) {
        this.ctmId = ctmId;
    }

    public String getCtmName() {
        return ctmName;
    }

    public void setCtmName(String ctmName) {
        this.ctmName = ctmName;
    }

    public String getrNick() {
        return rNick;
    }

    public void setrNick(String rNick) {
        this.rNick = rNick;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getReKkoUid() {
        return reKkoUid;
    }

    public void setReKkoUid(String reKkoUid) {
        this.reKkoUid = reKkoUid;
    }

    public Long gettAmount() {
        return tAmount;
    }

    public void settAmount(Long tAmount) {
        this.tAmount = tAmount;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public LocalDate gettDate() {
        return tDate;
    }

    public void settDate(LocalDate tDate) {
        this.tDate = tDate;
    }

    public LocalTime gettTime() {
        return tTime;
    }

    public void settTime(LocalTime tTime) {
        this.tTime = tTime;
    }

    public LocalDate getrDate() {
        return rDate;
    }

    public void setrDate(LocalDate rDate) {
        this.rDate = rDate;
    }

    public LocalTime getrTime() {
        return rTime;
    }

    public void setrTime(LocalTime rTime) {
        this.rTime = rTime;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public void setCancelCode(String cancelCode) {
        this.cancelCode = cancelCode;
    }

    public static SimTransDetail createNew(String rName, String rNick, String reKkoUid) {
        SimTransDetail simTransDetail = new SimTransDetail(null,
                "32333",
                null,
                null,
                rNick,
                rName,
                reKkoUid,
                null,
                null,
                null,
                null,
                null,
                null,
                "C0",
                "N");

        return simTransDetail;
    }
    public void edit(Long tAmount) {
        this.tAmount = tAmount;
    }

    public void editSend(Long tAmount) {
        this.tAmount = tAmount;
        this.commission = Long.valueOf(0);
        this.tDate = LocalDate.now();
        this.tTime = LocalTime.now();
        this.rDate = LocalDate.now().plusDays(1);
        this.rTime = LocalTime.now().minusHours(1);
        this.tCode = "C1";
    }
    public void editTcode(String tCode) {
        this.tCode = tCode;
    }
    public void editCancelCode() {
        this.cancelCode = "Y";
    }
}
