package com.quipux.playlistmanager.domains.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SongDTO {

    private Long id;
    private String title;
    private String artist;
    private Integer year;
    private String album;
    private String genre;
}
