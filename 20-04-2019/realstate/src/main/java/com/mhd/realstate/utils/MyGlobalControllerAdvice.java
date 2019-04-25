package com.mhd.realstate.utils;

import com.mhd.realstate.entity.User;
import com.mhd.realstate.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class MyGlobalControllerAdvice {
    @Autowired
    private UserRepo userRepo;

    @ModelAttribute("user")
    public User getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepo.findByUserName(auth.getName());
        return user;
    }
}
