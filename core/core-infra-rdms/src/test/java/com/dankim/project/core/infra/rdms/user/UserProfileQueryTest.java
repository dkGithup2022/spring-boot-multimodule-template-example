package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.QueryDslConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({UserProfileQuery.class, QueryDslConfiguration.class})
class UserProfileQueryTest {

    @Autowired
    private UserProfileEntityRepository userProfileRepository;


    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private UserProfileQuery userProfileQuery;


    private static final int ITERATION = 10;
    private static final String NICKNAME = "NICKNAME";

    @Test
    @DisplayName("nickname 인자로 리스트_ 성공")
    void listByUserNickname_success_01() {

        IntStream.range(0, ITERATION)
                .forEach(e -> saveUser(NICKNAME));

        var list = userProfileQuery.listByUserNickname(NICKNAME);
        assertEquals(ITERATION, list.size());

    }

    private void saveUser(String nickname) {
        UserEntity user = UserEntity.of(true);
        user = userRepository.save(user);
        userProfileRepository.save(UserProfileEntity.of(user.getId(), nickname));
    }


}