package com.dankim.project.common.commons.exceptions.clientException;

public class BadRequestException extends ClientException {
    protected BadRequestException(String status, String message) {
        super(status, message);
    }

    public BadRequestException(String message) {
        this("400", message);
    }
}
