package com.quipux.playlistmanager.domains.auth;

import com.quipux.playlistmanager.domains.auth.request.LoginRequest;
import com.quipux.playlistmanager.domains.auth.request.RegisterRequest;
import com.quipux.playlistmanager.domains.auth.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(AuthRoute.AUTH)
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(AuthRoute.REGISTER)
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody final RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(AuthRoute.LOGIN)
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody final LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
