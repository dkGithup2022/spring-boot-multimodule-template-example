package com.dankim.project.core.domain.user.api;

import com.dankim.project.common.commons.values.Nickname;
import com.dankim.project.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserReader {

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the found user or Optional.empty() if not found
     */
    Optional<User> get(Long userId);

    /**
     * Retrieves users by their nickname.
     *
     * @param nickname the nickname of the users to retrieve
     * @return a list of users with the given nickname
     */
    List<User> listUsersByNickname(Nickname nickname);
}