package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@Order(999)
public class JpaApplication implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            log.info("회원 {}", member);
        }

        Member member = memberRepository.findById(2L).orElseThrow();
        log.info("회원 with ID 2 {}", member);

        Article article = Article.builder()
                .title("스프링부트 강의 두번째 날...")
                .description("마이바티스랑 JPA를 배웠다. 어렵다.... 어지럽다...")
//                .created(LocalDateTime.now())
//                .updated(LocalDateTime.now())
                .member(member)
                .build();
        articleRepository.save(article);
        log.info("게시글 {}", article);

//        Member member = Member.builder()
//                    .id(5L)
//                    .name("김희선")
//                    .email("HeesunKim@hanbit.co.kr")
//                    .age(18).build();
//        memberRepository.save(member);
//        log.info("김희선 {}", member);

//        memberRepository.deleteById(5L);

//        List<Member> members = memberRepository.findByName("윤서준");
//        for (Member member : members) {
//            log.info("회원 {}", member);
//        }

//        Member member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr");
//        log.info("회원 {}", member);

//        List<Article> articles = articleRepository.findAll();
//        for (Article article : articles) {
//            log.info("게시글 {}", article);
//        }
    }
}
