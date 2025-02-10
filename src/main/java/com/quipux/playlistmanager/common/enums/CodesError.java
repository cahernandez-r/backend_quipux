package com.quipux.playlistmanager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodesError {

    CODE_ERROR_USER_ALREADY_EXIST("ERR002"),
    CODE_ERROR_USER_NOT_FOUND_USER("ERR003");
    private final String value;
}
