package com.xapp.xjava.controllers;

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
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.MovieIdReq;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.services.UsersService;

import com.xapp.xjava.entities.Movie;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/movies/watchlist")
public class WatchListController {
    
    @Autowired
    private UsersService usersService;

    @PostMapping("")
    ResponseEntity<User> handleWatchList(@AuthenticationPrincipal CustomUserDetails user, @RequestBody MovieIdReq req) throws Exception {
        String userName = user.getUsername();
        User newUser = usersService.handleWatchList(userName, req);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("")
    ResponseEntity<List<Movie>> getMyWatchlist(@AuthenticationPrincipal CustomUserDetails user) {
        String userName = user.getUsername();
        List<Movie> myWatchlist = usersService.getMyWatchlist(userName);
        return ResponseEntity.ok(myWatchlist);
    }
}
