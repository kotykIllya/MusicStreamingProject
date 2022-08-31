package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Playlist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;

import java.util.List;

public interface PlaylistService {

    Playlist getPlaylist(int id);
    List<Playlist> getAllUserPlaylistsByUserId(int id);
    Playlist createPlaylist(Playlist playlist);

}
