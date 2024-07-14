package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileEntityRepository extends JpaRepository<UserProfileEntity, Long>, IUserProfileEntityRepository {
    @Override
    UserProfileEntity save(UserProfileEntity userProfileEntity);

    @Override
    Optional<UserProfileEntity> findById(Long id);

    @Override
    List<UserProfileEntity> findByUserId(Long id);
}
