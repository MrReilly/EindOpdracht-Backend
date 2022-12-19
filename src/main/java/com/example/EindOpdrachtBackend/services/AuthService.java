package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User authenticateUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {

            return ((MyUserDetails) auth.getPrincipal()).getUser();
        }
        return null;

    }
}
