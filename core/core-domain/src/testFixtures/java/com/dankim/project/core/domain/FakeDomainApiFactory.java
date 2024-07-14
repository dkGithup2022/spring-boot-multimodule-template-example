package com.dankim.project.core.domain;

import com.dankim.project.core.domain.article.DefaultArticleReader;
import com.dankim.project.core.domain.article.DefaultArticleValidator;
import com.dankim.project.core.domain.article.DefaultArticleWriter;
import com.dankim.project.core.domain.article.api.ArticleReader;
import com.dankim.project.core.domain.article.api.ArticleWriter;
import com.dankim.project.core.domain.article.api.WebApiArticleValidator;
import com.dankim.project.core.domain.user.DefaultUserReader;
import com.dankim.project.core.domain.user.DefaultUserWriter;
import com.dankim.project.core.domain.user.api.UserReader;
import com.dankim.project.core.domain.user.api.UserWriter;
import com.dankim.project.core.infra.rdms.FakeDaoFactory;

public class FakeDomainApiFactory {

    private static ArticleReader fakeArticleReader
            = new DefaultArticleReader(
            FakeDaoFactory.getIArticleEntityRepository()
    );

    private static ArticleWriter fakeArticleWriter
            = new DefaultArticleWriter(
            FakeDaoFactory.getIArticleEntityRepository()
    );

    private static WebApiArticleValidator fakeWebApiArticleValidator
            = new DefaultArticleValidator(FakeDaoFactory.getIUserEntityRepository());

    private static UserReader fakeUserReader
            = new DefaultUserReader(
            FakeDaoFactory.getIUserEntityRepository(),
            FakeDaoFactory.getIUserProfileEntityRepository(),
            FakeDaoFactory.getIUserProfileQuery()
    );

    private static UserWriter fakeUserWriter
            = new DefaultUserWriter(
                    FakeDaoFactory.getIUserEntityRepository(),
            FakeDaoFactory.getIUserProfileEntityRepository()
    );

    public static ArticleReader getFakeArticleReader() {
        return fakeArticleReader;
    }

    public static ArticleWriter getFakeArticleWriter() {
        return fakeArticleWriter;
    }

    public static WebApiArticleValidator getFakeWebApiArticleValidator() {
        return fakeWebApiArticleValidator;
    }

    public static UserReader getFakeUserReader() {
        return fakeUserReader;
    }

    public static UserWriter getFakeUserWriter() {
        return fakeUserWriter;
    }
}
