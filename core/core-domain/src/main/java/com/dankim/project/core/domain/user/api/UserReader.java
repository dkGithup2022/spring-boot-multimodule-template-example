package com.dankim.project.core.domain.user.api;

import com.dankim.project.common.commons.values.Nickname;
import com.dankim.project.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserReader {
    /**
     * userId 를 기준으로 조회합니다.
     * @param userId
     * @return
     */

    Optional<User> get(Long userId);


    /**
     * nickname 을 기준으로 조회합니다.
     * @param nickname
     * @return
     */
    List<User> listUsersByNickname(Nickname nickname);
}
