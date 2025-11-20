package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.model.Member;
import com.example.demo.model.Product;
import com.example.demo.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/subscribe")
    public String getSubscribe(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public String postSubscribe(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult) {

        if (!Objects.equals(memberForm.getPassword(), memberForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.signup.password.mismatch", "비밀번호가 일치하지 않습니다.");
        }

        if (memberRepository.findByEmail(memberForm.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.signup.email.exist", "사용중인 이메일입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "subscribe";
        }

        Member member = Member.builder()
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .password(memberForm.getPassword()).build();
        memberRepository.save(member);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        model.addAttribute("id", 100);
        model.addAttribute("name", "윤서준");
        return "home";
    }

    @GetMapping("/member/add")
    public String getMember() {
        return "member-add";
    }

    @PostMapping("/member/add")
    public String postMember(Member member) {
        log.info("{}", member);
        memberRepository.save(member);
        // save to database
        return "redirect:/member/list";
    }

    @GetMapping("/member/edit")
    public String getMemberEdit(@RequestParam("id") Long id, Model model) {
        Member member = memberRepository.findById(id).orElseThrow();
        model.addAttribute("member", member);
        return "member-edit";
    }

    @PostMapping("/member/edit")
    public String postMemberEdit(Member member) {
        memberRepository.save(member);
        return "redirect:/member/list";
    }

    @GetMapping("/member/list")
    public String getMemberList(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "member-list";
    }

    @GetMapping("/member/delete")
    public String getMemberDelete(@RequestParam("id") Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
        return "redirect:/member/list";
    }

    @GetMapping("/product/list/{id}")
    public String getMember(Model model, @RequestParam("name") String name) {

        // get product from database
        List<Product> products = List.of(
                Product.builder().id(1L).name("애플와치").description("애플이 만든 스마트와치").price(2450000).build(),
                Product.builder().id(2L).name("갤럭시와치").description("삼성이 만든 스마트와치").price(230000).build(),
                Product.builder().id(3L).name("샤오미와치").description("샤오미가 만든 스마트와치").price(120000).build(),
                Product.builder().id(4L).name("캠퍼스와치").description("캠퍼스가 만든 스마트와치").price(50000).build()
        );

        model.addAttribute("title", "세상의 모든 스마트와치");
        model.addAttribute("now", new Date());
        model.addAttribute("products", products);
        model.addAttribute("showNow", false);
        return "product";
    }
}
