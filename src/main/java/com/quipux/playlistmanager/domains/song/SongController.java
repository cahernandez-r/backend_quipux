package com.quipux.playlistmanager.domains.song;

import com.quipux.playlistmanager.domains.song.response.FetchAllSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(Route.SONGS)
@CrossOrigin("*")
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<FetchAllSongResponse> fetchAllSongs() {
        return ResponseEntity.ok(songService.fetchAllSongs());
    }
}
