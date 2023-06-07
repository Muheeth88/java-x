package com.xapp.xjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.entities.Review;
import com.xapp.xjava.services.ReviewService;

@RestController
@RequestMapping("/movies/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("")
	ResponseEntity<Review> addReview(@RequestBody Review req) {
		Review savedReview = reviewService.addReview(req);
		return ResponseEntity.ok(savedReview);
	}

	@GetMapping("")
	ResponseEntity<List<Review>> getAllReviews() {
		List<Review> allReviews = reviewService.getAllReviews();
		return ResponseEntity.ok(allReviews);
	}

	@GetMapping("/{movieId}")
	ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable("movieId") Long movieId) {
		List<Review> movieReviews = reviewService.getReviewsByMovie(movieId);
		return ResponseEntity.ok(movieReviews);
	}
}
