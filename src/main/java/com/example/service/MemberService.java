package com.example.service;

import com.example.domain.Member;
import com.example.domain.MemberRequestDto;
import com.example.domain.MemberRepository;
import com.example.domain.MemberResponseDto;
import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long save(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .age(memberRequestDto.getAge())
                .statType(memberRequestDto.getStatType())
                .build();
        return memberRepository.save(member).getId();
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .statType(member.getStatType())
                .build();
    }
}
