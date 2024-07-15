package com.dankim.project.app.web.api.article;

import com.dankim.project.common.commons.exceptions.clientException.ForbiddenException;
import com.dankim.project.common.commons.exceptions.common.NotFoundException;

import com.dankim.project.core.domain.FakeDomainApiFactory;
import com.dankim.project.core.infra.rdms.FakeDaoFactory;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import com.dankim.project.core.infra.rdms.user.UserEntity;
import com.dankim.project.core.infra.rdms.user.UserProfileEntity;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  [ 테스트 예시 ]
 *
 *  가능한 pure 자바로 유지 .
 *  -> 테스트 속도
 *  -> 주입되는 객체에 대한 커스텀 가능 .
 */
class DefaultArticleApiServiceTest {

    ArticleApiService articleApiService = new DefaultArticleApiService(
            FakeDomainApiFactory.getFakeArticleReader(),
            FakeDomainApiFactory.getFakeArticleWriter(),
            FakeDomainApiFactory.getFakeWebApiArticleValidator()
    );


    IArticleEntityRepository articleRepository = FakeDaoFactory.getIArticleEntityRepository();
    IUserEntityRepository userRepository = FakeDaoFactory.getIUserEntityRepository();
    IUserProfileEntityRepository userProfileRepository = FakeDaoFactory.getIUserProfileEntityRepository();

    private static final String CONTENT = "CONTENT";
    private static final String TITLE = "TITLE";
    private static final String NICKNAME = "NICKNAME";

    @BeforeEach
    public void clearAll() {
        FakeDaoFactory.clearAll();
    }


    @Test
    @DisplayName("생성- 성공")
    void create_success_01() {
        // given - save available user
        var user = saveNewUser(true);

        // when -run sut
        var savedId = articleApiService.create(user.getId(), CONTENT, TITLE);
        var entity = articleRepository.findById(savedId).get();

        // then - assert
        assertEquals(CONTENT, entity.getContent());
        assertEquals(TITLE, entity.getTitle());
        assertEquals(user.getId(), entity.getUserId());
    }

    @Test
    @DisplayName("생성- 실패, 권한이 없는 유저")
    void create_fail_01() {
        // given - save unavailable user
        var user = saveNewUser(false);

        // when & then
        assertThrows(ForbiddenException.class, () -> articleApiService.create(user.getId(), CONTENT, TITLE));
    }


    @Test
    @DisplayName("생성- 실패, 존재하지 않는 유저")
    void create_fail_02() {
        // run sut when no user saved
        var USER_NOT_EXIEST = -1000L;
        assertThrows(NotFoundException.class, () -> articleApiService.create(USER_NOT_EXIEST, CONTENT, TITLE));
    }

    @Test
    @DisplayName("변경 - 성공 ")
    void update_success_01() {
        // given - save entities
        var entities = saveUserAndArticle();
        var user = entities.getLeft();
        var article = entities.getRight();

        //given - to be updated
        var UPDATE_CONTENT = "UPDATE_CONTENT";
        var UPDATE_TITLE = "UPDATE_TITLE";

        //when
        var updated = articleApiService.update(article.getId(), user.getId(), UPDATE_CONTENT, UPDATE_TITLE);
        var updatedEntity = articleRepository.findById(article.getId()).get();

        //then
        assertEquals(UPDATE_CONTENT, updated.getContent().get());
        assertEquals(UPDATE_CONTENT, updatedEntity.getContent());

        assertEquals(UPDATE_TITLE, updated.getTitle().get());
        assertEquals(UPDATE_TITLE, updatedEntity.getTitle());
    }

    @Test
    @DisplayName("변경- 실패, 권한이 없는 유저")
    void update_fail_01() {
        // given - save unavailable user
        var user = saveNewUser(false);
        var article = articleRepository.save(ArticleEntity.of(user.getId(), TITLE, CONTENT));

        // when & then
        assertThrows(ForbiddenException.class, () -> articleApiService.update(article.getId(), user.getId(), "UPDATE", "UPDATE"));

    }

    @Test
    @DisplayName("변경- 존재하지 않는 문서 ")
    void update_fail_02() {
        var USER_NOT_EXIEST = -1000L;
        var article = articleRepository.save(ArticleEntity.of(USER_NOT_EXIEST, TITLE, CONTENT));
        // when & then
        assertThrows(NotFoundException.class, () -> articleApiService.update(article.getId(), USER_NOT_EXIEST, "UPDATE", "UPDATE"));


    }


    @Test
    @DisplayName("조회 - 성공")
    void read_success_01() {

    }


    @Test
    @DisplayName("조회 실패 - 존재하지 않는 문서")
    void read_fail_01() {

    }


    private UserEntity saveNewUser(boolean available) {
        var user = userRepository.save(UserEntity.of(available));
        userProfileRepository.save(UserProfileEntity.of(user.getId(), NICKNAME));
        return user;
    }

    private Pair<UserEntity, ArticleEntity> saveUserAndArticle() {
        var user = saveNewUser(true);
        var article = articleRepository.save(ArticleEntity.of(user.getId(), TITLE, CONTENT));

        return Pair.of(user, article);
    }


}