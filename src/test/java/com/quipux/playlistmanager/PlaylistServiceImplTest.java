package com.quipux.playlistmanager;

import com.quipux.playlistmanager.common.entities.Playlist;
import com.quipux.playlistmanager.common.entities.PlaylistSong;
import com.quipux.playlistmanager.common.entities.Song;
import com.quipux.playlistmanager.common.exceptions.PlayListNotFoundException;
import com.quipux.playlistmanager.common.projections.PlaylistProjection;
import com.quipux.playlistmanager.common.repositories.PlayListRepository;
import com.quipux.playlistmanager.common.repositories.PlaylistSongRepository;
import com.quipux.playlistmanager.common.repositories.SongRepository;
import com.quipux.playlistmanager.domains.playlist.PlaylistMapper;
import com.quipux.playlistmanager.domains.playlist.PlaylistServiceImpl;
import com.quipux.playlistmanager.domains.playlist.dto.SongDTO;
import com.quipux.playlistmanager.domains.playlist.request.CreatePlayListRequest;
import com.quipux.playlistmanager.domains.playlist.response.CreatePlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.FetchDetailPlaylistResponse;
import com.quipux.playlistmanager.domains.playlist.response.ListPlaylistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistServiceImplTest {

    @Mock
    private PlayListRepository playListRepository;

    @Mock
    private PlaylistMapper playlistMapper;

    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @Mock
    private SongRepository songRepository;

    private PlaylistServiceImpl playlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playlistService = new PlaylistServiceImpl(playListRepository, playlistMapper, playlistSongRepository, songRepository);
    }

    @Test
    public void testFetchAllPlaylist() {

        List<PlaylistProjection> mockPlaylists = List.of(mock(PlaylistProjection.class));
        when(playListRepository.findByActiveTrue()).thenReturn(mockPlaylists);

        ListPlaylistResponse response = playlistService.fetchAllPlaylist();

        assertNotNull(response);
        assertEquals(mockPlaylists, response.getPlaylists());
    }

    @Test
    public void testFetchDetailPlaylist() {

        String listName = "Test Playlist";
        Playlist mockPlaylist = mock(Playlist.class);
        Song mockSong = mock(Song.class);
        PlaylistSong mockPlaylistSong = mock(PlaylistSong.class);

        when(playListRepository.findByNameAndActiveTrue(listName)).thenReturn(Optional.of(mockPlaylist));

        when(mockPlaylist.getPlaylistSongs()).thenReturn(new HashSet<>(List.of(mockPlaylistSong)));

        when(mockPlaylistSong.getSong()).thenReturn(mockSong);

        when(songRepository.findByIdAndActiveTrue(anyLong())).thenReturn(Optional.of(mockSong));

        FetchDetailPlaylistResponse mockResponse = mock(FetchDetailPlaylistResponse.class);
        when(playlistMapper.playlistToPlaylistDTO(mockPlaylist)).thenReturn(mockResponse);
        when(playlistMapper.songsToSongDTOs(anyList())).thenReturn(List.of(mock(SongDTO.class)));

        playlistService = new PlaylistServiceImpl(playListRepository, playlistMapper, playlistSongRepository, songRepository);

        FetchDetailPlaylistResponse response = playlistService.fetchDetailPlaylist(listName);

        assertNotNull(response, "The response should not be null");
    }

    @Test
    public void testDeletePlaylist() {
        String listName = "Test Playlist";
        Playlist mockPlaylist = mock(Playlist.class);
        when(playListRepository.findByNameAndActiveTrue(listName)).thenReturn(Optional.of(mockPlaylist));

        playlistService.deletePlaylist(listName);

        verify(mockPlaylist, times(1)).setActive(false);
    }

    @Test
    public void testDeletePlaylistNotFound() {

        String listName = "Test Playlist";
        when(playListRepository.findByNameAndActiveTrue(listName)).thenReturn(Optional.empty());

        assertThrows(PlayListNotFoundException.class, () -> playlistService.deletePlaylist(listName));
    }

    @Test
    public void testCreatePlayList() {
        CreatePlayListRequest request = new CreatePlayListRequest("New Playlist", "Description", List.of(1L));

        Playlist mockPlaylist = mock(Playlist.class);
        when(playlistMapper.playlistDTOToPlaylist(request)).thenReturn(mockPlaylist);
        when(playListRepository.save(mockPlaylist)).thenReturn(mockPlaylist);

        Song mockSong = mock(Song.class);
        when(songRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(mockSong));

        PlaylistSong mockPlaylistSong = mock(PlaylistSong.class);
        when(playlistSongRepository.save(mockPlaylistSong)).thenReturn(mockPlaylistSong);

        CreatePlaylistResponse mockResponse = mock(CreatePlaylistResponse.class);
        when(playlistMapper.playlistToCreatePlaylistResponse(mockPlaylist)).thenReturn(mockResponse);

        List<SongDTO> mockSongDTOs = List.of(mock(SongDTO.class));
        when(playlistMapper.songToSongDTO(mockSong)).thenReturn(mock(SongDTO.class));
        when(playlistMapper.songsToSongDTOs(anyList())).thenReturn(mockSongDTOs);

        playlistService = new PlaylistServiceImpl(playListRepository, playlistMapper, playlistSongRepository, songRepository);

        CreatePlaylistResponse response = playlistService.createPlayList(request);

        assertNotNull(response, "The CreatePlaylistResponse should not be null");
        assertTrue(response.getSongs().isEmpty(), "The songs list should not be empty");
    }

    @Test
    public void testCreatePlayListWithInvalidData() {
        CreatePlayListRequest invalidRequest = new CreatePlayListRequest("", "", List.of());

        assertThrows(ResponseStatusException.class, () -> playlistService.createPlayList(invalidRequest));
    }
}
