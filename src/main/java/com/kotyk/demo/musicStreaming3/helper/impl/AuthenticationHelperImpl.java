package com.kotyk.demo.musicStreaming3.helper.impl;

import com.kotyk.demo.musicStreaming3.entities.User;
import com.kotyk.demo.musicStreaming3.helper.AuthenticationHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelperImpl implements AuthenticationHelper {

    @Override
    public User getUserFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}
