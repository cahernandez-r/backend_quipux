package com.quipux.playlistmanager.domains.playlist.request;

import java.util.List;

public record CreatePlayListRequest (

    String name,
    String description,
    List<Long> idSongs
) { }
