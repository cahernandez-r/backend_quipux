package com.quipux.playlistmanager.domains.song.response;

import com.quipux.playlistmanager.domains.song.dto.SongDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FetchAllSongResponse {

    private List<SongDTO> songs;
}


