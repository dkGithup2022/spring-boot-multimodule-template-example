package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@NoArgsConstructor
public class UserProfileEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Setter

    private String nickname;

    private UserProfileEntity(Long id, Long userId, String nickname) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
    }

    public static UserProfileEntity of(Long userId, String nickname) {
        return new UserProfileEntity(null, userId, nickname);
    }
}
