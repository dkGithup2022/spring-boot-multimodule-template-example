package com.dankim.project.core.domain.user;

import com.dankim.project.core.domain.user.api.UserReader;
import com.dankim.project.core.infra.rdms.FakeDaoFactory;
import com.dankim.project.core.infra.rdms.user.UserEntity;
import com.dankim.project.core.infra.rdms.user.UserProfileEntity;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultUserReaderTest {
    IUserEntityRepository userRepository = FakeDaoFactory.getIUserEntityRepository();
    IUserProfileEntityRepository userProfileRepository = FakeDaoFactory.getIUserProfileEntityRepository();
    IUserProfileQuery userProfileQuery = FakeDaoFactory.getIUserProfileQuery();

    UserReader userReader = new DefaultUserReader(userRepository, userProfileRepository, userProfileQuery);


    private static final Boolean USER_AVAILABLE = true;
    private static final String NICKNAME = "NICKNAME";

    @Test
    @DisplayName("유저 도메인 조회 성공 ")
    void get_success() {

        var entity = userRepository.save(UserEntity.of(USER_AVAILABLE));
        userProfileRepository.save(UserProfileEntity.of(entity.getId(), NICKNAME));


        var domain = userReader.get(entity.getId()).get();

        assertEquals(NICKNAME, domain.getNickname().get());
        assertEquals(USER_AVAILABLE, domain.getAvailable());
    }
}