package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.domains.playlist.request.CreatePlayListRequest;
import com.quipux.playlistmanager.domains.playlist.response.CreatePlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ExistsPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;

public interface PlaylistService {

    ListPlaylistResponse fetchAllPlaylist();

    FetchDetailPlaylistResponse fetchDetailPlaylist(String listName);

    void deletePlaylist(String listName);

    CreatePlaylistResponse createPlayList(CreatePlayListRequest request);

    ExistsPlaylistResponse existsPlaylist(String listName);
}
