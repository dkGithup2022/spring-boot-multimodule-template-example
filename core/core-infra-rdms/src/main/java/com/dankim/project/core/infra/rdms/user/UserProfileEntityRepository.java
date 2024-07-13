package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileEntityRepository extends JpaRepository<UserProfileEntity, Long>, IUserProfileEntityRepository {
}
