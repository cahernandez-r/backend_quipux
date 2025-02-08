package com.quipux.playlistmanager.domains.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthRoute {

    public static final String AUTH = "auth";
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
}
