package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;

import java.util.List;

public interface GenreService {

    MusicObjectDTO getGenreById(int id);
    List<MusicObjectDTO> getAll();
    List<MusicObjectDTO> searchGenres(String query);

}
