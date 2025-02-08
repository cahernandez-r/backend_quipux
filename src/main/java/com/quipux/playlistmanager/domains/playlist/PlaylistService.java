package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;

public interface PlaylistService {

    ListPlaylistResponse fetchAllPlaylist();
}
