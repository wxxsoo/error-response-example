package com.example.service;

import com.example.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void save() {
        MemberRequestDto memberRequestDto = MemberRequestDto.builder().statType(MemberStatType.NORMAL).age(20).name("홍길동").build();
        Long saveId = memberService.save(memberRequestDto);
        Member member = memberRepository.findById(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(memberRequestDto.getName());
        Assertions.assertThat(member.getAge()).isEqualTo(memberRequestDto.getAge());
        Assertions.assertThat(member.getStatType()).isEqualTo(memberRequestDto.getStatType());
    }

    @Test
    void findById() {
        Member member = Member.builder().name("홍길동").age(20).statType(MemberStatType.NORMAL).build();
        Member created = memberRepository.save(member);
        MemberResponseDto memberResponseDto = memberService.findById(created.getId());
        Assertions.assertThat(memberResponseDto.getId()).isEqualTo(created.getId());
        Assertions.assertThat(memberResponseDto.getName()).isEqualTo(created.getName());
        Assertions.assertThat(memberResponseDto.getAge()).isEqualTo(created.getAge());
        Assertions.assertThat(memberResponseDto.getStatType()).isEqualTo(created.getStatType());
    }
}