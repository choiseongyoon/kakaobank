package com.kakaobank.daina.assignment.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CancelTar {
    private Long tId;
    private String accId;
    private String ctmId;
    private String reKkoUid;
    private LocalDate tDate;
    private LocalTime tTime;
    private LocalDate rDate;
    private LocalTime rTime;
    private String rCode;
    private String cSumup;

    public CancelTar() {
    }

    public CancelTar(Long tId, String accId, String ctmId, String reKkoUid, LocalDate tDate, LocalTime tTime, LocalDate rDate, LocalTime rTime, String rCode, String cSumup) {
        this.tId = tId;
        this.accId = accId;
        this.ctmId = ctmId;
        this.reKkoUid = reKkoUid;
        this.tDate = tDate;
        this.tTime = tTime;
        this.rDate = rDate;
        this.rTime = rTime;
        this.rCode = rCode;
        this.cSumup = cSumup;
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

    public String getReKkoUid() {
        return reKkoUid;
    }

    public void setReKkoUid(String reKkoUid) {
        this.reKkoUid = reKkoUid;
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

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public String getcSumup() {
        return cSumup;
    }

    public void setcSumup(String cSumup) {
        this.cSumup = cSumup;
    }
    public void editRcode(String rCode, String cSumup) {
        this.rCode = rCode;
        this.cSumup = cSumup;
    }
    public static CancelTar createNew(SimTransDetail simTransDetail) {
        return new CancelTar(
                simTransDetail.gettId(),
                simTransDetail.getAccId(),
                simTransDetail.getCtmId(),
                simTransDetail.getReKkoUid(),
                simTransDetail.gettDate(),
                simTransDetail.gettTime(),
                simTransDetail.getrDate().plusDays(1),
                simTransDetail.getrTime(),
                "N",
                ""
        );
    }
}
