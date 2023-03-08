package com.example.domain;

public enum MemberStatType {
    NORMAL("정상"),
    WITHDRAWAL("탈퇴");

    private String description;

    MemberStatType(String description) {
        this.description = description;
    }
}
