package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.entities.PlaylistSong;
import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.common.entities.general.EntityPrincipal;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import com.quipux.playlistmanager.common.repositories.PlayListRepository;
import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        final Optional<Playlist> opPlaylist = playListRepository.findByNameAndActiveTrue(listName);
        if (opPlaylist.isEmpty()) {
            return FetchDetailPlaylistResponse
                    .builder()
                    .isFoundedPlaylist(Boolean.FALSE)
                    .build();
        }
        final Playlist playlist = opPlaylist.get();
        final List<Song> songs = playlist.getPlaylistSongs()
                .stream()
                .map(PlaylistSong::getSong)
                .filter(EntityPrincipal::getActive)
                .toList();

        final List<SongDTO> songDTOS = playlistMapper.songsToSongDTOs(songs);
        final FetchDetailPlaylistResponse response = playlistMapper.playlistToPlaylistDTO(playlist);
        response.setSongs(songDTOS);
        response.setIsFoundedPlaylist(Boolean.TRUE);
        return response;
    }
}
