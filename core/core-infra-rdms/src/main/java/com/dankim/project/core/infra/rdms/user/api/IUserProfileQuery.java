package com.dankim.project.core.infra.rdms.user.api;

import com.dankim.project.core.infra.rdms.user.projections.UserProjection;

import java.util.List;

public interface IUserProfileQuery {

    List<UserProjection> listByUserNickname(String nickname);

}
