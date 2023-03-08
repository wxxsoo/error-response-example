package com.example.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "M001", "회원 정보가 없습니다."),
    INVALID_INPUT(400, "M002", "잘못된 입력입니다.");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
