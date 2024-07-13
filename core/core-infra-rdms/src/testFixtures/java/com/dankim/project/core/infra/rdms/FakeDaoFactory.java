package com.dankim.project.core.infra.rdms;

import com.dankim.project.core.infra.rdms.article.FakeIArticleEntityRepository;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import com.dankim.project.core.infra.rdms.user.FakeIUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.FakeIUserProfileEntityRepository;
import com.dankim.project.core.infra.rdms.user.FakeIUserProfileQuery;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileQuery;
import lombok.Getter;


public class FakeDaoFactory {
    private static final IUserEntityRepository iUserEntityRepository
            = new FakeIUserEntityRepository();

    private static final IUserProfileEntityRepository iUserProfileEntityRepository
            = new FakeIUserProfileEntityRepository();

    private static final IArticleEntityRepository iArticleEntityRepository
            = new FakeIArticleEntityRepository();

    private static final IUserProfileQuery iUserProfileQuery
            = new FakeIUserProfileQuery();

    public static IUserEntityRepository getIUserEntityRepository() {
        return iUserEntityRepository;
    }

    public static IUserProfileEntityRepository getIUserProfileEntityRepository() {
        return iUserProfileEntityRepository;
    }

    public static IArticleEntityRepository getIArticleEntityRepository() {
        return iArticleEntityRepository;
    }

    public static IUserProfileQuery getIUserProfileQuery() {
        return iUserProfileQuery;
    }
}
