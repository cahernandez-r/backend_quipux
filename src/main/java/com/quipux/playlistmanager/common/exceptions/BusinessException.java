package com.quipux.playlistmanager.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(final String message) {
        super(message);
    }
}
