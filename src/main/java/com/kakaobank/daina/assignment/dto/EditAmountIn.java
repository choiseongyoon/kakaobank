package com.kakaobank.daina.assignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditAmountIn {

    @NotNull(message = "송금금액을 입력하세요")
    private Long tAmount;

    public EditAmountIn() {
    }

    public EditAmountIn(Long tAmount) {
        this.tAmount = tAmount;
    }

    public Long gettAmount() {
        return tAmount;
    }

    public void settAmount(Long tAmount) {
        this.tAmount = tAmount;
    }
}
