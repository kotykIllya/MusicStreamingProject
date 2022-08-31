package com.kotyk.demo.musicStreaming3.service.implementation;

import com.kotyk.demo.musicStreaming3.dao.UserDao;
import com.kotyk.demo.musicStreaming3.entities.User;
import com.kotyk.demo.musicStreaming3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j(topic = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User saveToDB(User user) {
        User savedUser = userDao.save(user);
        log.info("User is saved" + savedUser);
        return savedUser;
    }

    @Override
    public User getById(int id) {
        return userDao.findById(id).orElse(new User());
    }





}