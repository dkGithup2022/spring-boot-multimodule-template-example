package com.dankim.project.app.web.api.user;

import com.dankim.project.core.domain.user.User;

import java.util.List;

public record UserListByNicknameResponse(List<User> users) {
}
