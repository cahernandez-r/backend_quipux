package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
