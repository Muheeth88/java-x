package com.xapp.xjava.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.entities.User;
import com.xapp.xjava.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    ResponseEntity<User> createTask(@RequestBody User req) {
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

    // @PutMapping("/{userId}")
    // ResponseEntity<UserEntity> editUser(@PathVariable("userId") Long userId,
    // @RequestBody UserEntity user) {
    // UserEntity editedUser = usersService.editUser(userId, user);
    // return ResponseEntity.ok(editedUser);
    // }

}
