package com.example.controller;

import com.example.domain.MemberRequestDto;
import com.example.domain.MemberResponseDto;
import com.example.error.BusinessException;
import com.example.error.CommonErrorResponse;
import com.example.error.ErrorCode;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/{id}")
    public MemberResponseDto create(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PostMapping("/members")
    public Long create(@RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.save(requestDto);
    }

    /*@ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }*/
}
