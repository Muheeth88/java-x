package com.xapp.xjava.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditReviewReq {
    private Long movieId;
    private String title;
	private String comment;
    private String reviewBy;
};
