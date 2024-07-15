package com.dankim.project.core.infra.rdms.article;

import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Long>, IArticleEntityRepository {

    @Override
    ArticleEntity save(ArticleEntity article);

    @Override
    Optional<ArticleEntity> findById(Long id);

    @Override
    List<ArticleEntity> getAllByUserId(Long userId);
}
