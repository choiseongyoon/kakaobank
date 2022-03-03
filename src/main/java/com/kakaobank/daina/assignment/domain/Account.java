package com.kakaobank.daina.assignment.domain;

import java.time.LocalDateTime;

public class Account {
    private Long accountId;
    private String nickname;
    private String korName;
    private String telNum;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Account() {
    }

    public Account(Long accountId, String nickname, String korName, String telNum, State state, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.korName = korName;
        this.telNum = telNum;
        this.state = state;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    //생성자로 만들지 않고, static 함수로 만들었는지
    //함수 명칭으로 기능에 대한 직관적인 이해 +
    //생성자로 만들 경우 service에서 이 로직을 구현해야함
    public static Account createNew(String nickname, String korName, String telNum) {
        return new Account(null,
                nickname,
                korName,
                telNum,
                State.NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now());
    }//static을 사용하면 new를 하지 않아도 메모리에 올리고 사용 가능하다. - 추가로 찾아보기

    public void remove() {
        this.state = State.DELETE;
        this.modifiedAt = LocalDateTime.now();
    }

    public void edit(String korName, String telNum, String state) {
        this.korName = korName;
        this.telNum = telNum;
        this.state = State.valueOf(state);
        this.modifiedAt = LocalDateTime.now();
    }
}
