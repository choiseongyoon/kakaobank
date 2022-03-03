package com.kakaobank.daina.assignment.dto;

public class CreateRnameIn {
    private String rName;
    private String rNick;
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
