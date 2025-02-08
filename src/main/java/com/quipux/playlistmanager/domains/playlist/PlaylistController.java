package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(Route.LISTS)
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<ListPlaylistResponse> fetchAllPlaylist() {
        return ResponseEntity.ok(playlistService.fetchAllPlaylist());
    }

    @PostMapping
    public ResponseEntity<ListPlaylistResponse> createPlaylist() {
        return ResponseEntity.ok(playlistService.fetchAllPlaylist());
    }

    @GetMapping(Route.LISTS_DETAIL)
    public ResponseEntity<FetchDetailPlaylistResponse> fetchDetailPlaylist(@PathVariable final String listName) {
        FetchDetailPlaylistResponse response = playlistService.fetchDetailPlaylist(listName);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(Route.LISTS_DELETE)
    public ResponseEntity<Void> deletePlaylist(@PathVariable final String listName) {
        playlistService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }
}
