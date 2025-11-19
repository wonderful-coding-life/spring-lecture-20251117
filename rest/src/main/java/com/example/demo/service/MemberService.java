package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public List<MemberResponse> subscribeBatch(List<MemberRequest> memberRequests) {
        // 회원 포인트 누적
        // 재고 차감
        // 외부결제시스템 연동 - 카드결제
        return memberRequests.stream().map(this::subscribe).toList();
    }

    public MemberResponse subscribe(MemberRequest memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .enabled(true)
                .password(memberRequest.getEmail()).build();

        memberRepository.save(member);

        // send welcome email
        // give welcome coupon, point

        return mapToMemberResponse(member);
    }

    public List<MemberResponse> findMembers(String name) {
        if (name == null) {
            return memberRepository.findAll().stream().map(this::mapToMemberResponse).toList();
        } else {
            return memberRepository.findByName(name).stream().map(this::mapToMemberResponse).toList();
        }
    }

    public MemberResponse findMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToMemberResponse(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge()).build();
    }
}
