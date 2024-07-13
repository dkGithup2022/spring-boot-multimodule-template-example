package com.dankim.project.core.infra.rdms.article.api;

import com.dankim.project.core.infra.rdms.article.ArticleEntity;

import java.util.List;
import java.util.Optional;

public interface IArticleEntityRepository {

    ArticleEntity save(ArticleEntity article);

    Optional<ArticleEntity> findById(Long id);

    List<ArticleEntity> getAllByUserId(Long userId);
}
