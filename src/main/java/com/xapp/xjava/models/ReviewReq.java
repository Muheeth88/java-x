package com.xapp.xjava.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReq {
    private String title; 
    private String comment;
    private Long movieId;
}
