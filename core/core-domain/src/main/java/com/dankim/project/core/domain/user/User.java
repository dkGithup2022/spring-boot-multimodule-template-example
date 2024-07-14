package com.dankim.project.core.domain.user;

import com.dankim.project.common.commons.values.Nickname;
import lombok.Getter;

@Getter
public class User {

    private final Long userId;
    private final Nickname nickname;
    private final Boolean available;

    private User(Long userId, Nickname nickname, Boolean available) {
        this.userId = userId;
        this.nickname = nickname;
        this.available = available;
    }

    public static User of(Long userId, Nickname nickname, Boolean available) {
        return new User(userId, nickname, available);
    }

    public static User of(Nickname nickname, Boolean available) {
        return new User(null, nickname, available);
    }


}
