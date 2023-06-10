package com.xapp.xjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.MovieIdReq;
import com.xapp.xjava.services.UsersService;

@RestController
@CrossOrigin("*")
@RequestMapping("/movies/likes")
public class LikeController {
    @Autowired
    private UsersService usersService;

    // --------------------------- handle Likes
    @PostMapping("")
    ResponseEntity<User> handleLikes(@AuthenticationPrincipal CustomUserDetails user, @RequestBody MovieIdReq req) throws Exception {
        String userName = user.getUsername();
        User newUser = usersService.handleLikes(userName, req);
        return ResponseEntity.ok(newUser);
    }

    // ---------------------- get My Like List
    @GetMapping("")
    ResponseEntity<List<Movie>> getMyLikelist(@AuthenticationPrincipal CustomUserDetails user) {
        String userName = user.getUsername();
        List<Movie> myLikelist = usersService.getMyLikelist(userName);
        return ResponseEntity.ok(myLikelist);
    }
}
