package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditTransferIn {
    @NotNull(message = "수취인 이름을 입력하세요")
    private String rName;
    @NotBlank(message = "닉네임을 입력하세요")
    private String rNick;
    @NotNull(message = "거래번호를 입력하세요")
    private Long tId;
    @NotBlank(message = "즐겨찾기 여부를 입력하세요")
    @Pattern(regexp = "Y|N", message = "유효한 상태 값이 아닙니다.")
    private String bookmark;

    public EditTransferIn() {
    }

    public EditTransferIn(String rName, String rNick, Long tId, String bookmark) {
        this.rName = rName;
        this.rNick = rNick;
        this.tId = tId;
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

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }
}
