package com.dankim.project.core.domain.article;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.core.domain.article.api.ArticleReader;
import com.dankim.project.core.infra.rdms.FakeDaoFactory;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultArticleReaderTest {
    IArticleEntityRepository repository = FakeDaoFactory.getIArticleEntityRepository();
    ArticleReader articleReader = new DefaultArticleReader(repository);

    private static final Long USER_ID = 1L;
    private static final String TITLE = "TITLE";
    private static final String CONTENT = "CONTENT";

    @BeforeEach
    public void clearAll() {
        FakeDaoFactory.clearAll();
    }

    @Test
    @DisplayName("조회 성공")
    void get_success() {
        var entity = repository.save(ArticleEntity.of(USER_ID, TITLE, CONTENT));

        var domain = articleReader.get(entity.getId()).get();

        assertEquals(TITLE, domain.getTitle().get());
        assertEquals(CONTENT, domain.getContent().get());
    }

    @Test
    @DisplayName("조회 실패 - 없는 문서")
    void get_fail() {
        var FAIL_ARTICLE_ID = -1L;
        assertThrows(NotFoundException.class, ()-> articleReader.get(FAIL_ARTICLE_ID));
    }


}