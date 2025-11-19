package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/members")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Member postMembers(@RequestBody Member member) {
        memberRepository.save(member);
        return member;
    }

    @GetMapping("/members")
    public List<Member> getMembers(@RequestParam(name="name", required = false) String name) {
        if (name == null || name.isBlank()) {
            return memberRepository.findAll();
        } else {
            return memberRepository.findByName(name);
        }
    }

    @GetMapping("/members/{id}")
    public Member getMembersById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/members/{id}")
    public Member putMembers(@PathVariable("id") Long id, @RequestBody Member member) {
        Member me =  memberRepository.findById(id).orElseThrow(NotFoundException::new);
        me.setName(member.getName());
        me.setEmail(member.getEmail());
        me.setAge(member.getAge());
        memberRepository.save(me);
        return me;
    }

    @PatchMapping("/members/{id}")
    public Member patchMembers(@PathVariable("id") Long id, @RequestBody Member member) {
        Member me =  memberRepository.findById(id).orElseThrow(NotFoundException::new);
        if (member.getName() != null) me.setName(member.getName());
        if (member.getEmail() != null) me.setEmail(member.getEmail());
        if (member.getAge() != null) me.setAge(member.getAge());
        memberRepository.save(me);
        return me;
    }

    @DeleteMapping("/members/{id}")
    public void deleteMembersById(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        memberRepository.delete(member);
    }
}
