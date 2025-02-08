package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.entities.PlaylistSong;
import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.common.entities.general.EntityPrincipal;
import com.quipux.playlistmanager.common.exceptions.PlayListNotFoundException;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import com.quipux.playlistmanager.common.repositories.PlayListRepository;
import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlayListRepository playListRepository;
    private final PlaylistMapper playlistMapper;

    @Override
    public ListPlaylistResponse fetchAllPlaylist() {
        final List<PlaylistProjection> playlists = playListRepository.findByActiveTrue();

        return ListPlaylistResponse
                .builder()
                .playlists(playlists)
                .build();
    }

    @Override
    @Transactional
    public FetchDetailPlaylistResponse fetchDetailPlaylist(final String listName) {

        final Playlist playlist = playListRepository.findByNameAndActiveTrue(listName)
                .orElseThrow(PlayListNotFoundException::new);

        final List<Song> songs = playlist.getPlaylistSongs()
                .stream()
                .map(PlaylistSong::getSong)
                .filter(EntityPrincipal::getActive)
                .toList();

        final List<SongDTO> songDTOS = playlistMapper.songsToSongDTOs(songs);
        final FetchDetailPlaylistResponse response = playlistMapper.playlistToPlaylistDTO(playlist);
        response.setSongs(songDTOS);
        return response;
    }

    @Override
    @Transactional
    public void deletePlaylist(final String listName) {
        final Playlist playlist = playListRepository.findByNameAndActiveTrue(listName)
                .orElseThrow(PlayListNotFoundException::new);
        playlist.setActive(Boolean.FALSE);
    }
}
