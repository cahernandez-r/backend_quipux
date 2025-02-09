package com.quipux.playlistmanager.domains.song;

import com.quipux.playlistmanager.domains.song.response.FetchAllSongResponse;

public interface SongService {

    FetchAllSongResponse fetchAllSongs();
}
