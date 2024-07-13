package com.dankim.project.core.infra.rdms.user.api;

import com.dankim.project.core.infra.rdms.user.UserEntity;

import java.util.Optional;

public interface IUserEntityRepository {

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);
}
