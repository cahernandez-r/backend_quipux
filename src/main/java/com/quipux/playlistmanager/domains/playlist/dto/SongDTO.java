package com.quipux.playlistmanager.domains.playlist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {

    private String title;
    private String artist;
    private String album;
    private String year;
    private String genre;
}
