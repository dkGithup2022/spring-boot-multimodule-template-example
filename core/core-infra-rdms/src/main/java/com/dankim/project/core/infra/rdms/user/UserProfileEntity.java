package com.dankim.project.core.infra.rdms.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class UserProfileEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private UserProfileEntity(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static UserProfileEntity of(String nickname) {
        return new UserProfileEntity(null, nickname);
    }
}
