package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/members")
    public Member postMembers(@RequestBody Member member) {
        memberRepository.save(member);
        return member;
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElseThrow();
    }
}
