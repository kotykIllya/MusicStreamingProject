package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.AlbumDao;
import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Album;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDao albumDao;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao, ModelMapper modelMapper) {
        this.albumDao = albumDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MusicObjectDTO getAlbumById(int id) {
        Optional<Album> byId = albumDao.findById(id);
        if(byId.isPresent()) {
            return modelMapper.map(byId.get(), MusicObjectDTO.class);
        }
        return new MusicObjectDTO();
    }

    @Override
    public List<MusicObjectDTO> getAll() {
        List<Album> all = albumDao.findAll();
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> getAllAlbumsFromArtistByArtistId(int id) {
        List<Album> all = albumDao.findAllAlbumsByArtistId(id);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> getAllAlbumsFromGenreByGenreId(int id) {
        List<Album> all = albumDao.findAllAlbumsByGenreId(id);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> searchAlbums(String query) {
        List<Album> all = albumDao.searchAlbums(query);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

}
