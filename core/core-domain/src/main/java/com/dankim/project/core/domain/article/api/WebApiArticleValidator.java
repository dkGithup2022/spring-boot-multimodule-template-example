package com.dankim.project.core.domain.article.api;

import com.dankim.project.core.domain.article.Article;

public interface WebApiArticleValidator {
    boolean canCreate(Long userId);
    boolean canUpdate(Article article, Long userId);
}
