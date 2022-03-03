package com.kakaobank.daina.assignment.dto;

public class RemoveAccountIn {
    private Long accountId;

    public RemoveAccountIn() {
    }

    public RemoveAccountIn(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
