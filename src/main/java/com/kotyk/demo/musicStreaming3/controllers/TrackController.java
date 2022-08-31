package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.models.Playlist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/track")
public class TrackController {

    private final TrackService trackService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final PlaylistService playlistService;

    @Autowired
    public TrackController(TrackService trackService, AlbumService albumService, ArtistService artistService, GenreService genreService, PlaylistService playlistService) {
        this.trackService = trackService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
        this.playlistService = playlistService;

    }

    @GetMapping("/{id}")
    public String getTrackById(Model model, @PathVariable int id) {
        MusicObjectDTO musicObjectDTO = trackService.getTrackById(id);
        model.addAttribute("musicObjectDTO", musicObjectDTO);
        return "trackPage";
    }

    @GetMapping("/all")
    public String getTracks(Model model){
        List<MusicObjectDTO> musicObjectDTOS = trackService.getAll();
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        return "allTracks";
    }

    @GetMapping("/search/")
    public String search(Model model, Model model1, Model model2, Model model3, @RequestParam String query) {
        List<MusicObjectDTO> musicObjectDTOS = trackService.searchTracks(query);
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        List<MusicObjectDTO> musicObjectDTOS1 = genreService.searchGenres(query);
        model1.addAttribute("musicObjectDTOS1", musicObjectDTOS1);
        List<MusicObjectDTO> musicObjectDTOS2 = artistService.searchArtists(query);
        model2.addAttribute("musicObjectDTOS2", musicObjectDTOS2);
        List<MusicObjectDTO> musicObjectDTOS3 = albumService.searchAlbums(query);
        model3.addAttribute("musicObjectDTOS3", musicObjectDTOS3);
        return "searchPage";
    }

    @PostMapping("/addTrackToPlaylist")
    public String addTrackToPlaylist(Model model, Model model1, @RequestParam int playlistId, @RequestParam int trackId) {
        Playlist playlist = playlistService.getPlaylist(playlistId);
        MusicObjectDTO musicObjectDTO = trackService.getTrackByIdForAddingToPlaylist(trackId);
        Track track = trackService.getTrackNotMusicObjectDTO(trackId);
        List<Playlist> playlists = track.getPlaylists();
        playlists.add(playlist);
        track.setPlaylists(playlists);
        trackService.saveToDB(track);
        model.addAttribute("playlist", playlist);
        model1.addAttribute("musicObjectDTO", musicObjectDTO);
        return "index";
    }

}
