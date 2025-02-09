package com.quipux.playlistmanager.common.repositories;

import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.domains.song.dto.SongDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT new com.quipux.playlistmanager.domains.song.dto.SongDTO(song.id, song.title, song.artist, song.year, "
            + "alb.description, gen.description)"
            + "FROM Song song "
            + "JOIN song.album alb "
            + "JOIN song.genre gen "
            + "WHERE song.active = true")
    List<SongDTO> fetchAllSongs();

    Optional<Song> findByIdAndActiveTrue(Long id);
}
