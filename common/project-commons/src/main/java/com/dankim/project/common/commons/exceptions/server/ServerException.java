package com.dankim.project.common.commons.exceptions.server;

import com.dankim.project.common.commons.exceptions.ApplicationException;

public class ServerException extends ApplicationException {

    protected ServerException(String status, String message) {
        super(status, message);
    }

    public ServerException(String message) {
        this("500", message);
    }
}
