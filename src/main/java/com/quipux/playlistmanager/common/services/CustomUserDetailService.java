package com.quipux.playlistmanager.common.services;

import com.quipux.playlistmanager.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByEmailAndActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
