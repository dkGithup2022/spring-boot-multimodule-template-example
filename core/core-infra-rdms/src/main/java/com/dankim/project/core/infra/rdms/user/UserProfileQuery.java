package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.user.api.IUserProfileQuery;
import com.dankim.project.core.infra.rdms.user.projections.UserProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dankim.project.core.infra.rdms.user.QUserEntity.userEntity;
import static com.dankim.project.core.infra.rdms.user.QUserProfileEntity.userProfileEntity;

@Component
@RequiredArgsConstructor
public class UserProfileQuery implements IUserProfileQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserProjection> listByUserNickname(String nickname) {
        QUserProfileEntity userProfile = QUserProfileEntity.userProfileEntity;
        QUserEntity user = QUserEntity.userEntity;

        return queryFactory
                .select(Projections.constructor(UserProjection.class,
                        user.id,
                        user.available,
                        userProfile.nickname
                ))
                .from(userProfile)
                .join(user).on(userProfile.id.eq(user.id))
                .where(userProfile.nickname.eq(nickname))
                .fetch();
    }
}
