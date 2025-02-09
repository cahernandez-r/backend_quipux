package com.quipux.playlistmanager.domains.playlist.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreatePlayListRequest(
    @NotBlank
    String name,
    @NotBlank
    String description,
    List<Long> idSongs
) { }
