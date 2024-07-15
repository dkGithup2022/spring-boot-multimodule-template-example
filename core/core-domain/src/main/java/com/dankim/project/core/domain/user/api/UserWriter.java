package com.dankim.project.core.domain.user.api;

import com.dankim.project.core.domain.user.User;



public interface UserWriter {

    /**
     * Saves a new user.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    User newUser(User user);

    /**
     * Updates user information and returns the updated user.
     * Only the nickname can be changed. An exception is thrown if any other information is changed.
     *
     * @param user the user with updated information
     * @return the updated user
     * @throws IllegalArgumentException if any information other than the nickname is changed
     */
    User update(User user);
}