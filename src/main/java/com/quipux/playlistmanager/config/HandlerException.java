package com.quipux.playlistmanager.config;

import com.quipux.playlistmanager.common.exceptions.PlayListNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class HandlerException {

    @ExceptionHandler({PlayListNotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundException(final PlayListNotFoundException e) {
        log.error("HandlerException.handlerNotFoundException {e}", e);
        return ResponseEntity.notFound().build();
    }

}
