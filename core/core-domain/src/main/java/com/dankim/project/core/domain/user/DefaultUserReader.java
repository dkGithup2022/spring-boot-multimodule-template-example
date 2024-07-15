package com.dankim.project.core.domain.user;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.common.commons.exceptions.server.ServerException;
import com.dankim.project.common.commons.values.Nickname;
import com.dankim.project.core.domain.user.api.UserReader;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultUserReader implements UserReader {

    private final IUserEntityRepository userEntityRepository;
    private final IUserProfileEntityRepository userProfileEntityRepository;
    private final IUserProfileQuery userProfileQuery;

    @Override
    public Optional<User> get(Long userId) {
        var user = userEntityRepository.
                findById(userId).orElseThrow(() -> new NotFoundException("can not find entity with id | id : " + userId));
        var profiles = userProfileEntityRepository.findByUserId(userId);

        if (profiles.size() > 1)
            throw new ServerException("user id 에 해당하는 유저가 1개 이상입니다. | id: " + userId);

        if (profiles.isEmpty())
            return Optional.empty();

        return Optional.of(User.of(userId, Nickname.of(profiles.getFirst().getNickname()), user.getAvailable()));
    }

    @Override
    public List<User> listUsersByNickname(Nickname nickname) {
        return userProfileQuery.listByUserNickname(nickname.get()).stream()
                .map(user -> User.of(user.getUserId(), Nickname.of(user.getNickname()), user.getAvailable())).toList();
    }
}
