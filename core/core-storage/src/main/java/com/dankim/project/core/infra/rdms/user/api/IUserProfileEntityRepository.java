package com.dankim.project.core.infra.rdms.user.api;

import com.dankim.project.core.infra.rdms.user.UserProfileEntity;

import java.util.List;
import java.util.Optional;

public interface IUserProfileEntityRepository {

    UserProfileEntity save(UserProfileEntity userProfileEntity);

    Optional<UserProfileEntity> findById(Long id);

    List<UserProfileEntity> findByUserId(Long id);
}
