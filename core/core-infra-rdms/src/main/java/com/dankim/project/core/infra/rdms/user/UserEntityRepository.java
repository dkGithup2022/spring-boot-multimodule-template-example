package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, IUserEntityRepository {
}
