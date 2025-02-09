package com.quipux.playlistmanager.domains.auth;

import com.quipux.playlistmanager.common.entities.User;
import com.quipux.playlistmanager.common.enums.CodesError;
import com.quipux.playlistmanager.common.exceptions.BusinessException;
import com.quipux.playlistmanager.common.repositories.UserRepository;
import com.quipux.playlistmanager.config.JwtTokenProvider;
import com.quipux.playlistmanager.domains.auth.request.LoginRequest;
import com.quipux.playlistmanager.domains.auth.request.RegisterRequest;
import com.quipux.playlistmanager.domains.auth.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtService;

    @Override
    public JwtAuthenticationResponse register(final RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        Optional<User> opUser = userRepository.findUserByEmailAndActiveTrue(request.getEmail());
        if (opUser.isPresent()) {
            throw new BusinessException(CodesError.CODE_ERROR_USER_ALREADY_EXIST.getValue());
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        var jwt = jwtService.createToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse login(final LoginRequest request) {
        User user = userRepository.findUserByEmailAndActiveTrue(request.getEmail())
                .orElseThrow(() -> new AccessDeniedException(CodesError.CODE_ERROR_USER_NOT_FOUND_USER.getValue()));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var jwt = jwtService.createToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
