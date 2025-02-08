package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import com.quipux.playlistmanager.common.repositories.PlayListRepository;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlayListRepository playListRepository;

    @Override
    public ListPlaylistResponse fetchAllPlaylist() {
        final List<PlaylistProjection> playlists = playListRepository.findByActiveTrue();

        return ListPlaylistResponse
                .builder()
                .playlists(playlists)
                .build();
    }
}
