package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.dto.MusicObjectDTO;
import com.kotyk.demo.musicStreaming3.entities.User;
import com.kotyk.demo.musicStreaming3.entities.models.Playlist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import com.kotyk.demo.musicStreaming3.helper.AuthenticationHelper;
import com.kotyk.demo.musicStreaming3.service.PlaylistService;
import com.kotyk.demo.musicStreaming3.service.TrackService;
import com.kotyk.demo.musicStreaming3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    private final UserService userService;
    private final PlaylistService playlistService;
    private final AuthenticationHelper authenticationHelper;
    private final TrackService trackService;

    @Autowired
    public PlaylistController(UserService userService, PlaylistService playlistService, AuthenticationHelper authenticationHelper, TrackService trackService) {
        this.userService = userService;
        this.playlistService = playlistService;
        this.authenticationHelper = authenticationHelper;
        this.trackService = trackService;
    }

    @GetMapping("/{id}")
    public String getPlaylistBYId(Model model, Model model1, @PathVariable int id) {
        Playlist playlist = playlistService.getPlaylist(id);
        List<MusicObjectDTO> musicObjectDTOS = trackService.getAllPlaylistTracksByPlaylistId(id);
        model.addAttribute("playlist", playlist);
        model1.addAttribute("musicObjectDTOS", musicObjectDTOS);
        return "playlistPage";
    }

    @GetMapping("/allUserPlaylists/{id}")
    public String getPlaylists(Model model, Model model1, @PathVariable int id){
        List<Playlist> playlists = playlistService.getAllUserPlaylistsByUserId(id);
        model.addAttribute("playlists", playlists);
        User user = userService.getById(id);
        model1.addAttribute("user", user);
        return "myPlaylists";
    }

    @PostMapping("/create")
    public String createPlaylist(Model model, @RequestParam String playlistName) {
        User user = userService.getById(authenticationHelper.getUserFromSession().getId());
        Playlist playlist = new Playlist();
        playlist.setName(playlistName);
        playlist.setUser(user);
        Playlist savedPlaylist = playlistService.createPlaylist(playlist);
        model.addAttribute("playlist", savedPlaylist);
        return "playlistPage";
    }

}
