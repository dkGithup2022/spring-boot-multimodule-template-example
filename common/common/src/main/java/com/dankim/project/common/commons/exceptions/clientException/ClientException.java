package com.dankim.project.common.commons.exceptions.clientException;

import com.dankim.project.common.commons.exceptions.ApplicationException;

public abstract class ClientException extends ApplicationException {
    protected ClientException(String status, String message) {
        super(status, message);
    }
}
