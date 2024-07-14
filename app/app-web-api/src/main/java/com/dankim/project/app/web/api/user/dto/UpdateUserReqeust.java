package com.dankim.project.app.web.api.user.dto;

public record UpdateUserReqeust(Long articleId, String nickname, Boolean available) {
}
