package com.kakaobank.daina.assignment.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiHis {
    private String reKkoUid;
    private String rName;
    private String rNick;
    private String bookmark;
    private LocalDate tDate;
    private LocalTime tTime;

    public ReceiHis() {
    }

    public ReceiHis(String reKkoUid, String rName, String rNick, String bookmark, LocalDate tDate, LocalTime tTime) {
        this.reKkoUid = reKkoUid;
        this.rName = rName;
        this.rNick = rNick;
        this.bookmark = bookmark;
        this.tDate = tDate;
        this.tTime = tTime;
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

    public String getrNick() {
        return rNick;
    }

    public void setrNick(String rNick) {
        this.rNick = rNick;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
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


}
