package com.xapp.xjava.models;

import com.xapp.xjava.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONLogin {
    private String token;
    private User user;
    
}
