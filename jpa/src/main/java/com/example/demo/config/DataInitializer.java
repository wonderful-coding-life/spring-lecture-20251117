package com.example.demo.config;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(0)
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            List<Member> members = List.of(
                    Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                    Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                    Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(21).build(),
                    Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
            );
            memberRepository.saveAll(members);
        }
    }
}
