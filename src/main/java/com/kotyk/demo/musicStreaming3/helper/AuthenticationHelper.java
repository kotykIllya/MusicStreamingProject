package com.kotyk.demo.musicStreaming3.helper;

import com.kotyk.demo.musicStreaming3.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationHelper  {


     User getUserFromSession();
}
