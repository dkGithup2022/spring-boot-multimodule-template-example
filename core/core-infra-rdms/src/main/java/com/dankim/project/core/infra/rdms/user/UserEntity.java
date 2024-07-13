package com.dankim.project.core.infra.rdms.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean available;

    private UserEntity(Long id, Boolean available) {
        this.id = id;
        this.available = available;
    }

    public static UserEntity of(Boolean available) {
        return new UserEntity(null, available);
    }
}