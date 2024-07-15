package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, IUserEntityRepository {

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);
}
