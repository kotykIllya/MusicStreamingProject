package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Album;

import java.util.List;

public interface AlbumService {

    MusicObjectDTO getAlbumById(int id);
    List<MusicObjectDTO> getAll();
    List<MusicObjectDTO> getAllAlbumsFromArtistByArtistId(int id);
    List<MusicObjectDTO> getAllAlbumsFromGenreByGenreId(int id);
    List<MusicObjectDTO> searchAlbums(String query);

}
