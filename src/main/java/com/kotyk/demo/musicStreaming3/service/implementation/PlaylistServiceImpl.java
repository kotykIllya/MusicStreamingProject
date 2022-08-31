package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.PlaylistDao;
import com.kotyk.demo.musicStreaming3.entities.models.Playlist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistDao playlistDao;

    @Autowired
    public PlaylistServiceImpl(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    @Override
    public Playlist getPlaylist(int id) {
        return playlistDao.findById(id).orElse(new Playlist());
    }

    @Override
    public List<Playlist> getAllUserPlaylistsByUserId(int id) {
        List<Playlist> all = playlistDao.findAllByUserId(id);
        return all;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        Playlist playlist1 = playlistDao.save(playlist);
        return playlist1;
    }

}
