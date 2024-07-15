package com.dankim.project.common.commons.exceptions.common;

import com.dankim.project.common.commons.exceptions.CommonException;

public class NotFoundException extends CommonException {
    public NotFoundException(String message) {
        super(message);
    }
}
