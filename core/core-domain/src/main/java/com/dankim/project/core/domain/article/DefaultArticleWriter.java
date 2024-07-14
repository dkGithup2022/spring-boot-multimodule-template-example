package com.dankim.project.core.domain.article;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.common.commons.values.Content;
import com.dankim.project.common.commons.values.Title;
import com.dankim.project.core.domain.article.api.ArticleWriter;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class DefaultArticleWriter implements ArticleWriter {

    private final IArticleEntityRepository articleEntityRepository;

    @Override
    public Article update(Article article) {
        var entity = articleEntityRepository.findById(article.getArticleId())
                .orElseThrow(() -> new NotFoundException("can not find article with id | id : " + article.getArticleId()));


        entity.setTitle(article.getTitle().get());
        entity.setTitle(article.getContent().get());

        return Article.of(
                entity.getUserId(),
                Content.of(entity.getContent()),
                Title.of(entity.getTitle())
        ).addArticleId(entity.getId());
    }

    @Override
    public Article newArticle(Article article) {

        var entity = articleEntityRepository
                .save(
                        ArticleEntity.of(article.getAuthorId(), article.getTitle().get(), article.getContent().get())
                );

        return Article.of(
                entity.getUserId(),
                Content.of(entity.getContent()),
                Title.of(entity.getTitle())
        ).addArticleId(entity.getId());
    }
}
