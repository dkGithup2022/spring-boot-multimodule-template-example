package com.dankim.project.core.domain.user.api;

import com.dankim.project.core.domain.user.User;

public interface UserWriter {

    /**
     * [ api 설명 예시 입니다. ]
     *  user 를 저장합니다.
     * @param user
     * @return
     */
    User newUser(User user);

    /**
     * [ api 설명 예시 입니다. ]
     * user 정보를 update 후 반환합니다.
     * nickname 만 바꿀 수 있으며, nickname 이외의 다른 정보가 바뀐경우 exception 을 발생시킵니다.
     *
     * @param user
     * @return
     */
    User update(User user);
}
