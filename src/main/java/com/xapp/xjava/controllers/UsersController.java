package com.xapp.xjava.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.MovieIdReq;
import com.xapp.xjava.models.UserIdReq;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.services.UsersService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
	private UsersRepository usersRepository;

    @PostMapping("")
    ResponseEntity<User> createUser(@RequestBody User req) {
        User newUser = usersService.createUser(req);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = usersService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    ResponseEntity<Optional<User>> getUser(@PathVariable("userId") Long userId) {
        Optional<User> user = usersService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // @GetMapping("/{userName}")
    // ResponseEntity<Optional<UserEntity>> getUser(@PathVariable("userName") String
    // userName) {
    // Optional<UserEntity> user = usersService.getUser(userName);
    // return ResponseEntity.ok(user);
    // }

    @PutMapping("/{userId}")
    ResponseEntity<User> editUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        User editedUser = usersService.editUser(userId, user);
    return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping("")
    ResponseEntity<?> deleteUser(@AuthenticationPrincipal CustomUserDetails user, @RequestBody UserIdReq req) {
      
        String userName = user.getUsername();
        User userDetails = usersRepository.findByEmail(userName);
        System.out.println(userDetails.getRole());
        if(userDetails.getRole().equalsIgnoreCase("ADMIN")) {
            usersService.deleteUser(req);
        } else {
            System.out.println("You are not an Admin to delete a user!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return null;
        }
        return  ResponseEntity.noContent().build();
    }

}
