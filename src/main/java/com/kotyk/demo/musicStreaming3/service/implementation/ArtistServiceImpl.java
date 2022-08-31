package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.ArtistDao;
import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Artist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDao artistDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDao artistDao, ModelMapper modelMapper) {
        this.artistDao = artistDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MusicObjectDTO getArtistById(int id) {
        Optional<Artist> byId = artistDao.findById(id);
        if( byId.isPresent()) {
            return modelMapper.map(byId.get(), MusicObjectDTO.class);
        }
        return new MusicObjectDTO();
    }

    @Override
    public List<MusicObjectDTO> getAll() {
        List<Artist> all = artistDao.findAll();
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> searchArtists(String query) {
        List<Artist> all = artistDao.searchArtists(query);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }
}
