package com.quipux.playlistmanager.domains.auth;

import com.quipux.playlistmanager.domains.auth.request.LoginRequest;
import com.quipux.playlistmanager.domains.auth.request.RegisterRequest;
import com.quipux.playlistmanager.domains.auth.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse login(LoginRequest request);
}
