package com.dankim.project.app.web.api.article.dto;

public record CreateArticleRequest(Long userId, String title, String content) {
}
