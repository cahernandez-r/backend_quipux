package com.quipux.playlistmanager.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayListNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PlayListNotFoundException() {
        super("Playlist Not Found");
    }
}
