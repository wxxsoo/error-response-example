package com.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class MemberRequestDto {

    @NotNull
    private MemberStatType statType;
    @NotNull(message = "이름을 입력해주세요.")
    private String name;
    @NotNull(message = "나이를 입력해주세요.")
    @Positive(message = "양수만 입력 가능합니다.")
    private Integer age;

    @Builder
    public MemberRequestDto(MemberStatType statType, String name, Integer age) {
        this.statType = statType;
        this.name = name;
        this.age = age;
    }
}
