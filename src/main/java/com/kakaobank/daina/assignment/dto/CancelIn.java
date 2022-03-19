package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CancelIn {
    @NotNull(message = "수취할 수 없는 거래입니다.")
    private Long tId;

    public CancelIn() {
    }

    public CancelIn(Long tId) {
        this.tId = tId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

}
