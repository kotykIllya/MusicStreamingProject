package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.service.AlbumService;
import com.kotyk.demo.musicStreaming3.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;

    @Autowired
    public ArtistController(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public String getTracks(Model model){
        List<MusicObjectDTO> musicObjectDTOS = artistService.getAll();
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        return "allArtists";
    }

    @GetMapping("/allArtistAlbums/{id}")
    public String getArtistAlbums(Model model, Model model1, @PathVariable int id){
        List<MusicObjectDTO> musicObjectDTOS =  albumService.getAllAlbumsFromArtistByArtistId(id);
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        MusicObjectDTO musicObjectDTO1 = artistService.getArtistById(id);
        model1.addAttribute("musicObjectDTO1", musicObjectDTO1);
        return "allArtistAlbums";
    }

}
