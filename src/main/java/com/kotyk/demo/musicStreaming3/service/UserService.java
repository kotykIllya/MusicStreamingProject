package com.kotyk.demo.musicStreaming3.service;

import com.kotyk.demo.musicStreaming3.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User saveToDB(User user);
    User getById(int id);

}

