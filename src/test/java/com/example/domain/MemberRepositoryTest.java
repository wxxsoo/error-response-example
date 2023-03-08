package com.example.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder().name("홍길동").age(20).statType(MemberStatType.NORMAL).build();
        Member created = memberRepository.save(member);
        Member findMember = memberRepository.findById(created.getId()).get();
        Assertions.assertThat(findMember).isEqualTo(created);
    }

}