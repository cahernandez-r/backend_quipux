package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(Route.PLAY_LIST)
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping(Route.LISTS)
    public ResponseEntity<ListPlaylistResponse> fetchAllPlaylist() {
        return ResponseEntity.ok(playlistService.fetchAllPlaylist());
    }

    @GetMapping(Route.LISTS_DETAIL)
    public ResponseEntity<FetchDetailPlaylistResponse> fetchDetailPlaylist(@PathVariable final String listName) {
        FetchDetailPlaylistResponse response = playlistService.fetchDetailPlaylist(listName);
        if (!response.getIsFoundedPlaylist()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
