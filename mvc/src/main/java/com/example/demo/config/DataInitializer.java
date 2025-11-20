package com.example.demo.config;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            var members = List.of(
                    Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").password("password").build(),
                    Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").password("password").build()
            );
            memberRepository.saveAll(members);
        }
        if (articleRepository.count() == 0) {
            var member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr").orElseThrow();
            for (int i = 0; i < 100; i++) {
                var article = Article.builder()
                        .title("제목 " + i)
                        .description("본문 " + i)
                        .member(member).build();
                articleRepository.save(article);
            }
        }

    }
}
