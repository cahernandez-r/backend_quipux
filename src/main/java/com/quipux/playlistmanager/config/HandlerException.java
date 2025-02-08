package com.quipux.playlistmanager.config;

import com.quipux.playlistmanager.common.exceptions.PlayListNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler({PlayListNotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundPlayListException(final PlayListNotFoundException e) {
        log.error("HandlerException.handlerNotFoundException {e}", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handlerException(final Exception e) {
        log.error("HandlerException.handlerException {e}", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
