package com.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberResponseDto {

    private Long id;
    private MemberStatType statType;
    private String name;
    private int age;

    @Builder
    public MemberResponseDto(Long id, MemberStatType statType, String name, int age) {
        this.id = id;
        this.statType = statType;
        this.name = name;
        this.age = age;
    }
}
