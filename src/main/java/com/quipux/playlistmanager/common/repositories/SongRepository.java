package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByIdAndActiveTrue(Long id);
}
