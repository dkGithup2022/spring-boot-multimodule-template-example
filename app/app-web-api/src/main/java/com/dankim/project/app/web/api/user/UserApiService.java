package com.dankim.project.app.web.api.user;

import com.dankim.project.core.domain.user.User;

import java.util.List;

public interface UserApiService {

    /**
     * 유저를 조회합니다.
     * <p>
     * [ exception ]
     * - BadRequestException (400) : id에 해당하는 유저가 없는 경우.
     *
     * @param userId
     * @return user:User | 조회된 유저를 리턴합니다.
     */
    User get(Long userId);

    /**
     * 유저 정보를 업데이트합니다.
     * nickname과 available 상태를 변경할 수 있습니다.
     * <p>
     * [ exception ]
     * - BadRequestException (400) : id에 해당하는 유저가 없는 경우.
     * - ForbiddenException (403) : 업데이트 권한이 없는 경우.
     *
     * @param userId
     * @param nickname
     * @param available
     * @return user:User | 업데이트된 유저를 리턴합니다.
     */
    User update(Long userId, String nickname, Boolean available);


    /**
     * 유저를 생성합니다.
     * <p>
     * [ exception ]
     * - BadRequestException (400) : 닉네임이 유효하지 않은 경우.
     *
     * @param nickname
     * @return id:Long | 생성된 유저의 ID를 리턴합니다.
     */
    Long create(String nickname);

    List<User> listByNickname(String nickname);
}
