package com.quipux.playlistmanager.domains.playlist.response;

import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CreatePlaylistResponse {

    private Long id;
    private String name;
    private String description;
    private List<SongDTO> songs;
}
