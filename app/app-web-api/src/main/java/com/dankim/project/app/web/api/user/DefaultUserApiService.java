package com.dankim.project.app.web.api.user;

import com.dankim.project.common.commons.exceptions.clientException.ForbiddenException;
import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.common.commons.values.Nickname;
import com.dankim.project.core.domain.user.User;
import com.dankim.project.core.domain.user.api.UserReader;
import com.dankim.project.core.domain.user.api.UserValidator;
import com.dankim.project.core.domain.user.api.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserApiService implements UserApiService {

    private final UserReader userReader;
    private final UserWriter userWriter;
    private final UserValidator userValidator;

    @Override
    public User get(Long userId) {
        return userReader.get(userId)
                .orElseThrow(() -> new NotFoundException("id 에 해당하는 유저가 없습니다."))
                ;
    }

    @Override
    public List<User> listByNickname(String nickname){
        return userReader.listUsersByNickname(Nickname.of(nickname));
    }

    @Override
    public User update(Long userId, String nickname, Boolean available) {

        var user = userReader.get(userId)
                .orElseThrow(() -> new NotFoundException("id 에 해당하는 유저가 없습니다."));

        if (!userValidator.doValidate(userId))
            throw new ForbiddenException("update 할 수 없는 userId 입니다.");

        return userWriter.update(newUserForUpdate(user, nickname, available));
    }

    @Override
    public Long create(String nickname) {
        var created = userWriter.update(User.of(Nickname.of(nickname), true));
        return created.getUserId();
    }

    private User newUserForUpdate(User user, String nickname, Boolean available) {
        return User.of(
                user.getUserId(),
                nickname == null || nickname.isEmpty() ? user.getNickname() : Nickname.of(nickname),
                available == null ? user.getAvailable() : available
        );

    }


}
