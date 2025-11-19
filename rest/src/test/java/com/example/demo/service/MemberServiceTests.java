package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository.save(Member.builder().name("테스트사용자").email("TestUser@hanbit.co.kr").age(10).build());
    }

    @AfterEach
    public void afterEach() {
        var member = memberRepository.findByEmail("TestUser@hanbit.co.kr").orElseThrow();
        memberRepository.delete(member);
    }

    @Test
    public void testFindById() {
        var member = memberService.findMemberByEmail("TestUser@hanbit.co.kr");
        assertThat(member.getName()).isEqualTo("테스트사용자");
        assertThat(member.getAge()).isEqualTo(10);
    }
}
