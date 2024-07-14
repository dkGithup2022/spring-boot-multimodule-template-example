package com.dankim.project.core.domain.user;

import com.dankim.project.core.domain.user.api.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserValidator implements UserValidator {
    @Override
    public Boolean doValidate(Long userId) {
        return true;
    }
}
