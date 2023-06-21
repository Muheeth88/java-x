package com.xapp.xjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetailsService;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.JSONLogin;
import com.xapp.xjava.models.JwtRequest;
import com.xapp.xjava.models.JwtResponse;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.utility.JwtUtility;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
	private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            
            throw new Exception("Wrong Credentials!");
        } catch (BadCredentialsException e) {
            throw new Exception("Wrong Credentials!!!");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        User user = usersRepository.findByEmail(jwtRequest.getEmail());
        String token = this.jwtUtility.generateToken(userDetails);
        JSONLogin loginObj = new JSONLogin();
        loginObj.setToken(token);
        loginObj.setUser(user);

        return ResponseEntity.ok(loginObj);
    }
}