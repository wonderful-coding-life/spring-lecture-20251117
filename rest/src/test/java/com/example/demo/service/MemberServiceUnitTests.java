package com.example.demo.service;

import com.example.demo.dto.MemberResponse;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceUnitTests {
    @MockitoBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void testFindById() {
//        when(memberRepository.findById(2L)).thenReturn(
//                Optional.ofNullable(Member.builder()
//                        .id(2L)
//                        .name("윤광철")
//                        .email("KwangcheolYoon@hanbit.co.kr")
//                        .age(43).build())
//        );
//
//        var member = memberService.findMemberById(2L);
//
//        assertThat(member.getId()).isEqualTo(2L);
//        assertThat(member.getName()).isEqualTo("윤광철");
//        assertThat(member.getAge()).isEqualTo(43);
    }
}
