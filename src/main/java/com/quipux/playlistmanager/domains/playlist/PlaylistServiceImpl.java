package com.quipux.playlistmanager.domains.playlist;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.entities.PlaylistSong;
import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.common.entities.general.EntityPrincipal;
import com.quipux.playlistmanager.common.exceptions.PlayListNotFoundException;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import com.quipux.playlistmanager.common.repositories.PlayListRepository;
import com.quipux.playlistmanager.common.repositories.PlaylistSongRepository;
import com.quipux.playlistmanager.common.repositories.SongRepository;
import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import com.quipux.playlistmanager.domains.playlist.request.CreatePlayListRequest;
import com.quipux.playlistmanager.domains.playlist.response.CreatePlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ExistsPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlayListRepository playListRepository;
    private final PlaylistMapper playlistMapper;
    private final PlaylistSongRepository playlistSongRepository;
    private final SongRepository songRepository;

    @Override
    public ListPlaylistResponse fetchAllPlaylist() {
        final List<PlaylistProjection> playlists = playListRepository.findByActiveTrue();

        return ListPlaylistResponse
                .builder()
                .playlists(playlists)
                .build();
    }

    @Override
    @Transactional
    public FetchDetailPlaylistResponse fetchDetailPlaylist(final String listName) {

        final Playlist playlist = playListRepository.findByNameAndActiveTrue(listName)
                .orElseThrow(PlayListNotFoundException::new);

        final List<Song> songs = playlist.getPlaylistSongs()
                .stream()
                .map(PlaylistSong::getSong)
                .filter(EntityPrincipal::getActive)
                .toList();

        final List<SongDTO> songDTOS = playlistMapper.songsToSongDTOs(songs);
        final FetchDetailPlaylistResponse response = playlistMapper.playlistToPlaylistDTO(playlist);
        response.setSongs(songDTOS);
        return response;
    }

    @Override
    @Transactional
    public void deletePlaylist(final String listName) {
        final Playlist playlist = playListRepository.findByNameAndActiveTrue(listName)
                .orElseThrow(PlayListNotFoundException::new);
        playlist.setActive(Boolean.FALSE);
    }

    @Override
    @Transactional
    public CreatePlaylistResponse createPlayList(final CreatePlayListRequest request) {
        final Boolean nameIsInvalid = request.name() == null || request.name().isBlank();
        final Boolean descriptionIsInvalid = request.description() == null || request.description().isBlank();
        if (nameIsInvalid || descriptionIsInvalid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name or description is not valid");
        }
        Playlist playlist = playlistMapper.playlistDTOToPlaylist(request);
        playListRepository.save(playlist);

        final CreatePlaylistResponse response = playlistMapper.playlistToCreatePlaylistResponse(playlist);

        List<SongDTO> songs = relatePlaylistSongs(request.idSongs(), playlist);
        response.setSongs(songs);
        return response;
    }

    @Override
    public ExistsPlaylistResponse existsPlaylist(final String listName) {
        Optional<Playlist> opPlaylist = playListRepository.findByNameAndActiveTrue(listName);
        return ExistsPlaylistResponse
                .builder()
                .existsPlayList(opPlaylist.isPresent())
                .build();
    }

    private List<SongDTO> relatePlaylistSongs(final List<Long> idSongs, final Playlist playlist) {
        List<SongDTO> songDTOS = new ArrayList<>();
        idSongs.forEach(idSong -> {
            Optional<Song> opSong = songRepository.findByIdAndActiveTrue(idSong);
            if (opSong.isEmpty()) {
                return;
            }
            PlaylistSong playlistSong = new PlaylistSong();
            playlistSong.setSong(opSong.get());
            playlistSong.setPlaylist(playlist);
            playlistSongRepository.save(playlistSong);
            songDTOS.add(playlistMapper.songToSongDTO(opSong.get()));
        });
        return  songDTOS;
    }
}
