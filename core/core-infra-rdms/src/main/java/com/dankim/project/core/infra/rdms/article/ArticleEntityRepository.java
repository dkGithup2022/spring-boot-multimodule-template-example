package com.dankim.project.core.infra.rdms.article;

import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Long>, IArticleEntityRepository {

    List<ArticleEntity> listByUserId(Long userId);
}
