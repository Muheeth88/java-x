package com.xapp.xjava.entities;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	private String title;

	private String comment;

	private String reviewBy;

	private ZonedDateTime reviewTime;

	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="movie_id", referencedColumnName = "movieId")
	private Long movieId;

	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "user_id", referencedColumnName = "UserId" )
	private Long userId;

    public void setTime(LocalDate now) {
    }

}
