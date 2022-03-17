package com.kakaobank.daina.assignment.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class CtmBaccClose {
    private Long tId;
    private String ctmId;
    private String ctmName;
    private String resiNum;
    private String pNum;
    private LocalDate tDate;
    private LocalTime tTime;
    private String rCode;
    private String cSumup;

    public CtmBaccClose() {
    }

    public CtmBaccClose(Long tId, String ctmId, String ctmName, String resiNum, String pNum, LocalDate tDate, LocalTime tTime, String rCode, String cSumup) {
        this.tId = tId;
        this.ctmId = ctmId;
        this.ctmName = ctmName;
        this.resiNum = resiNum;
        this.pNum = pNum;
        this.tDate = tDate;
        this.tTime = tTime;
        this.rCode = rCode;
        this.cSumup = cSumup;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
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

    public String getResiNum() {
        return resiNum;
    }

    public void setResiNum(String resiNum) {
        this.resiNum = resiNum;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
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

    public static CtmBaccClose createNew(SimTransDetail simTransDetail, AccInfo accInfo) {
        return new CtmBaccClose(
                simTransDetail.gettId(),
                accInfo.getCtmId(),
                accInfo.getCtmName(),
                null,
                null,
                simTransDetail.gettDate(),
                simTransDetail.gettTime(),
                "N",
                "계좌해지고객"
        );
    }
}
