package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.service.AlbumService;
import com.kotyk.demo.musicStreaming3.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;
    private final AlbumService albumService;

    @Autowired
    public GenreController(GenreService genreService, AlbumService albumService) {
        this.genreService = genreService;
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public String getTracks(Model model){
        List<MusicObjectDTO> musicObjectDTOS = genreService.getAll();
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        return "allGenres";
    }

    @GetMapping("/allGenreAlbums/{id}")
    public String getGenreAlbums(Model model, Model model1, @PathVariable int id){
        List<MusicObjectDTO> musicObjectDTOS = albumService.getAllAlbumsFromGenreByGenreId(id);
        model.addAttribute("musicObjectDTOS", musicObjectDTOS);
        MusicObjectDTO musicObjectDTO1 = genreService.getGenreById(id);
        model1.addAttribute("musicObjectDTO1", musicObjectDTO1);
        return "allGenreAlbums";
    }

}
