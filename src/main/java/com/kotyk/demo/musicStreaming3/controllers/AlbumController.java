package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.service.AlbumService;
import com.kotyk.demo.musicStreaming3.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;
    private final TrackService trackService;

    @Autowired
    public AlbumController(AlbumService albumService, TrackService trackService) {
        this.albumService = albumService;
        this.trackService = trackService;
    }

    @GetMapping("/all")
    public String getAlbums(Model model){
        List<MusicObjectDTO> musicObjectDTOS = albumService.getAll();
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        return "allAlbums";
    }

    @GetMapping("/allAlbumTracks/{id}")
    public String getAlbumTracks(Model model, Model model1, @PathVariable int id){
        List<MusicObjectDTO> musicObjectDTOS =  trackService.getAllTracksFromAlbumByAlbumId(id);
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        MusicObjectDTO musicObjectDTO1 = albumService.getAlbumById(id);
        model1.addAttribute("musicObjectDTO1", musicObjectDTO1);
        return "allAlbumTracks";
    }

}
