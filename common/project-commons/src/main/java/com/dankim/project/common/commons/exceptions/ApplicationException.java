package com.dankim.project.common.commons.exceptions;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {
    private final String status;
    private final String message;

    protected ApplicationException(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
