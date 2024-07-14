package com.dankim.project.app.web.api.user.dto;

import com.dankim.project.core.domain.user.User;

public record GetUserResponse(User found) {
}
