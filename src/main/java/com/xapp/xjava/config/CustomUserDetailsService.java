package com.xapp.xjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.User;
import com.xapp.xjava.repositories.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
        User user = usersRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(email + " User Not Foundx!");
        }

        return new CustomUserDetails(user);
    }
    
}
