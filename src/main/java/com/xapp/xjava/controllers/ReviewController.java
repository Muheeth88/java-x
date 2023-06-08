package com.xapp.xjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.Review;
import com.xapp.xjava.models.EditReviewReq;
import com.xapp.xjava.models.ReviewReq;
import com.xapp.xjava.services.ReviewService;

@RestController
@RequestMapping("/movies/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("")
	ResponseEntity<Review> addReview(@AuthenticationPrincipal CustomUserDetails user, @RequestBody ReviewReq req) {
		String userName = user.getUsername();
		Review savedReview = reviewService.addReview(userName, req);
		return ResponseEntity.ok(savedReview);
	}

	@PutMapping("/{reviewId}") 
	ResponseEntity<Review> editReview(@PathVariable("reviewId") Long reviewId, @AuthenticationPrincipal CustomUserDetails user, @RequestBody EditReviewReq req ) throws Exception {
		String userName = user.getUsername();
		Review editedReview = reviewService.editReview(userName, reviewId, req);
		return ResponseEntity.ok(editedReview);
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
