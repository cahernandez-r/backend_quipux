package com.quipux.playlistmanager.domains.playlist.response;

import com.quipux.playlistmanager.common.dto.PaginationDTO;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class ListPlaylistResponse extends PaginationDTO implements Serializable {

    private List<PlaylistProjection> playlists;
}
