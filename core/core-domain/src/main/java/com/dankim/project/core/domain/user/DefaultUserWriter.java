package com.dankim.project.core.domain.user;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.common.commons.exceptions.server.ServerException;
import com.dankim.project.common.commons.values.Nickname;
import com.dankim.project.core.domain.user.api.UserWriter;
import com.dankim.project.core.infra.rdms.user.UserEntity;
import com.dankim.project.core.infra.rdms.user.UserProfileEntity;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class DefaultUserWriter implements UserWriter {

    private final IUserEntityRepository userEntityRepository;
    private final IUserProfileEntityRepository userProfileEntityRepository;

    @Override
    public User newUser(User user) {
        var userEntity = userEntityRepository.save(
                UserEntity.of(user.getAvailable())
        );

        var userProfile = userProfileEntityRepository.save(
                UserProfileEntity.of(
                        userEntity.getId(),
                        user.getNickname().get()
                )
        );

        return User.of(userEntity.getId(), Nickname.of(userProfile.getNickname()), userEntity.getAvailable());
    }

    @Override
    public User update(User user) {

        var userEntity = userEntityRepository.findById(user.getUserId())
                .orElseThrow(() -> new NotFoundException("can not find"));

        var userProfiles = userProfileEntityRepository.findByUserId(userEntity.getId());
        if (userProfiles.size() != 1)
            throw new ServerException("...");


        userEntity.setAvailable(user.getAvailable());
        userProfiles.getFirst().setNickname(user.getNickname().get());

        return User.of(userEntity.getId(), Nickname.of(userProfiles.getFirst().getNickname()), userEntity.getAvailable());
    }
}
