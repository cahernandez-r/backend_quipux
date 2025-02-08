package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndActiveTrue(String email);
}
