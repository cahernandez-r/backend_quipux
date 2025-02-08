package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.common.entities.Album;
import com.quipux.playlistmanager.common.entities.Genre;
import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import com.quipux.playlistmanager.domains.playlist.request.CreatePlayListRequest;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description")
    })
    FetchDetailPlaylistResponse playlistToPlaylistDTO(Playlist playlist);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "album.description", target = "album"),
            @Mapping(source = "genre.description", target = "genre")
    })
    List<SongDTO> songsToSongDTOs(List<Song> songs);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description")
    })
    Playlist playlistDTOToPlaylist(CreatePlayListRequest request);

    default String map(Album album) {
        return album != null ? album.getDescription() : null;
    }

    default String map(Genre genre) {
        return genre != null ? genre.getDescription() : null;
    }
}
