package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.request.CreatePlayListRequest;
import com.quipux.playlistmanager.domains.playlist.response.CreatePlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
    public ResponseEntity<CreatePlaylistResponse> createPlaylist(@RequestBody final CreatePlayListRequest request) {
        CreatePlaylistResponse response = playlistService.createPlayList(request);
        return ResponseEntity.created(URI.create(String.format("lists/%d",response.getId()))).body(response);
    }

    @GetMapping(Route.LISTS_DETAIL)
    public ResponseEntity<FetchDetailPlaylistResponse> fetchDetailPlaylist(@PathVariable final String listName) {
        return ResponseEntity.ok(playlistService.fetchDetailPlaylist(listName));
    }

    @DeleteMapping(Route.LISTS_DELETE)
    public ResponseEntity<Void> deletePlaylist(@PathVariable final String listName) {
        playlistService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }
}
