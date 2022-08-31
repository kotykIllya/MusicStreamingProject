package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.AlbumDao;
import com.kotyk.demo.musicStreaming3.dao.TrackDao;
import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Album;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.TrackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;
    private final ModelMapper modelMapper;

    @Autowired
    public TrackServiceImpl(TrackDao trackDao, ModelMapper modelMapper) {
        this.trackDao = trackDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MusicObjectDTO getTrackById(int id) {
        Optional<Track> byId = trackDao.findById(id);
        if( byId.isPresent()) {
            return modelMapper.map(byId.get(), MusicObjectDTO.class);
        }
        return new MusicObjectDTO();
    }

    @Override
    public List<MusicObjectDTO> getAll() {
        List<Track> all = trackDao.findAll();
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Track saveToDB(Track track) {
        Track savedTrack = trackDao.save(track);
        return savedTrack;
    }

    @Override
    public void transferFile(MultipartFile file) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "musicForStreaming" + File.separator;
        System.out.println(pathToFolder);
        try {
            file.transferTo(new File(pathToFolder + file.getOriginalFilename()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<MusicObjectDTO> getAllTracksFromAlbumByAlbumId(int id) {
        List<Track> all = trackDao.findAllTracksByAlbumId(id);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MusicObjectDTO> searchTracks(String query) {
        List<Track> all = trackDao.searchTracks(query);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MusicObjectDTO getTrackByIdForAddingToPlaylist(int id) {
        Optional<Track> byId = trackDao.findById(id);
        if( byId.isPresent()) {
            return modelMapper.map(byId.get(), MusicObjectDTO.class);
        }
        return new MusicObjectDTO();
    }

    @Override
    public List<MusicObjectDTO> getAllPlaylistTracksByPlaylistId(int id) {
        List<Track> all = trackDao.findAllPlaylistTracksByPlaylistId(id);
        modelMapper.map(all, List.class);
        return all.stream().map(musicObject -> modelMapper.map(musicObject, MusicObjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Track getTrackNotMusicObjectDTO(int id) {
        return trackDao.findById(id).get();
    }

}
