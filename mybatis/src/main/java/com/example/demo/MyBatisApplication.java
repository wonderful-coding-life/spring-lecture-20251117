package com.example.demo;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyBatisApplication implements ApplicationRunner {
    private final MemberMapper memberMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Long id = 9L;
//        Member me = Member.builder()
//                .name("김희선")
//                .email("HeesunKim@hanbit.co.kr")
//                .age(18).build();
//        memberMapper.updateById(id, me);

//        memberMapper.deleteById(id);

//        List<Member> members = memberMapper.selectAll();
        String order = "email";
        String direction = "asc";
        List<Member> members = memberMapper.selectAllOrderBy(order, direction);
        for (Member member : members) {
            log.info("회원 {}", member);
        }

//        log.info("윤서준 {}", memberMapper.selectByEmail("SeojunYoon@hanbit.co.kr"));
    }
}
