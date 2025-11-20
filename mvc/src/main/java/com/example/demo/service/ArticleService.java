package com.example.demo.service;

import com.example.demo.dto.ArticleResponse;
import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Page<ArticleResponse> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(this::mapToArticleResponse);
    }

    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail()).build();
    }
}
