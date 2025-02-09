package com.quipux.playlistmanager.domains.song;

import com.quipux.playlistmanager.common.repositories.SongRepository;
import com.quipux.playlistmanager.domains.song.dto.SongDTO;
import com.quipux.playlistmanager.domains.song.response.FetchAllSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public FetchAllSongResponse fetchAllSongs() {
        List<SongDTO> songs = songRepository.fetchAllSongs();
        return FetchAllSongResponse
                .builder()
                .songs(songs)
                .build();
    }
}
