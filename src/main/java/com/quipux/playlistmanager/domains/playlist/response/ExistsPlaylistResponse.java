package com.quipux.playlistmanager.domains.playlist.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExistsPlaylistResponse {

    private Boolean existsPlayList;
}
