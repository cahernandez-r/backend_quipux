package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
}
