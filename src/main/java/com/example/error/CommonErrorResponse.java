package com.example.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonErrorResponse {

    private int status;
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomFieldError> errors;

    public CommonErrorResponse(ErrorCode errorCode, List<CustomFieldError> errors) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = errors;
    }

    public CommonErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static CommonErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new CommonErrorResponse(errorCode, CustomFieldError.of(bindingResult));
    }

    public static CommonErrorResponse of(ErrorCode errorCode) {
        return new CommonErrorResponse(errorCode);
    }

    public static CommonErrorResponse of(ErrorCode errorCode, List<CustomFieldError> errors) {
        return new CommonErrorResponse(errorCode, errors);
    }

}
