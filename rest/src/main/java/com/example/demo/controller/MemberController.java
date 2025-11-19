package com.example.demo.controller;

import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ArticleService articleService;

//    @PostMapping("/members")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public MemberResponse postMembers(@RequestBody MemberRequest memberRequest) {
//        return memberService.subscribe(memberRequest);
//    }

    @PostMapping("/members")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MemberResponse postMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.subscribe(memberRequest);
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers(@RequestParam(name="name", required = false) String name) {
        return memberService.findMembers(name);
    }

    @GetMapping("/members/{id}")
    public MemberResponse getMembersById(@PathVariable("id") Long id) {
        return memberService.findMemberById(id);
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

    @PostMapping("/members/{id}/articles")
    public ArticleResponse postArticles(@PathVariable("id") Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.write(id, articleRequest);
    }
}
