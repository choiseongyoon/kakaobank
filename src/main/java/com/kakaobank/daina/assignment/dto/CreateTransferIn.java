package com.kakaobank.daina.assignment.dto;

public class CreateTransferIn {
    private String rName;
    private String rNick;
    private String bookmark;

    public CreateTransferIn() {
    }

    public CreateTransferIn(String rName, String rNick, String bookmark) {
        this.rName = rName;
        this.rNick = rNick;
        this.bookmark = bookmark;
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
}
