package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    MusicObjectDTO getTrackById(int id);
    List<MusicObjectDTO> getAll();
    Track saveToDB(Track track);
    void transferFile(MultipartFile file);
    List<MusicObjectDTO> getAllTracksFromAlbumByAlbumId(int id);
    List<MusicObjectDTO> searchTracks(String query);
    MusicObjectDTO getTrackByIdForAddingToPlaylist(int id);
    List<MusicObjectDTO> getAllPlaylistTracksByPlaylistId(int id);
    Track getTrackNotMusicObjectDTO(int id);

}
