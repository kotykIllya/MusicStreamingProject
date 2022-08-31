package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;

import java.util.List;

public interface ArtistService {

    MusicObjectDTO getArtistById(int id);
    List<MusicObjectDTO> getAll();
    List<MusicObjectDTO> searchArtists(String query);

}
