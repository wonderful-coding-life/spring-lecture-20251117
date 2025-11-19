package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void testFindById() {
        var member = memberService.findMemberById(1L);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("윤서준");
        assertThat(member.getAge()).isEqualTo(10);
    }
}
