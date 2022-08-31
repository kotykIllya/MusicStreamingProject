package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.GenreDao;
import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Genre;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final ModelMapper modelMapper;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao, ModelMapper modelMapper) {
        this.genreDao = genreDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MusicObjectDTO getGenreById(int id) {
        Optional<Genre> byId = genreDao.findById(id);
        if( byId.isPresent()) {
            return modelMapper.map(byId.get(), MusicObjectDTO.class);
        }
        return new MusicObjectDTO();
    }

    @Override
    public List<MusicObjectDTO> getAll() {
        List<Genre> all = genreDao.findAll();
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> searchGenres(String query) {
        List<Genre> all = genreDao.searchGenres(query);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }
}
