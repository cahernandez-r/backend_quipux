package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayListRepository extends JpaRepository<Playlist, Long> {

    Page<PlaylistProjection> findByActiveTrue(Pageable pageable);

    Optional<Playlist> findByNameAndActiveTrue(String name);
}
