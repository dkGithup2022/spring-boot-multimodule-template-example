package com.dankim.project.app.web.api.article.dto;

public record UpdateArticleRequest(
        Long articleId,
        Long userId,
        String content,
        String title
) {
}
