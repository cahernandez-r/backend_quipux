package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<Playlist, Long> {

    List<PlaylistProjection> findByActiveTrue();
}
