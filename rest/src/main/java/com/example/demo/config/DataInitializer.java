package com.example.demo.config;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Component
@Slf4j
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private List<RequestMappingHandlerMapping> mappings;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            Member member = Member.builder()
                    .name("윤서준")
                    .email("SeojunYoon@hanbit.co.kr")
                    .age(10).build();
            memberRepository.save(member);
            for (int i = 0; i < 100; i++) {
                Article article = Article.builder()
                        .title("스프링 강의 " + i + "일차")
                        .description("스프링 강의가 재미있다.")
                        .member(member).build();
                articleRepository.save(article);
            }
        }

        mappings.forEach(mapping ->
                mapping.getHandlerMethods().forEach((info, method) ->
                        log.info("info {} -> {}", info, method))
        );
    }
}
