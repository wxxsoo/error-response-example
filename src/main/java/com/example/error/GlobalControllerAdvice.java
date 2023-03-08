package com.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

//    @ExceptionHandler(BusinessException.class)
//    public CommonErrorResponse handleBusinessException(BusinessException e) {
//        return CommonErrorResponse.of(e.getErrorCode());
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CommonErrorResponse errorResponse = CommonErrorResponse.of(ErrorCode.INVALID_INPUT, e.getBindingResult());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ErrorCode.INVALID_INPUT.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<CommonErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        CommonErrorResponse errorResponse = CommonErrorResponse.of(ErrorCode.INVALID_INPUT);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ErrorCode.INVALID_INPUT.getStatus()));
    }
}
