package com.xapp.xjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.Review;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.EditReviewReq;
import com.xapp.xjava.models.MovieIdReq;
import com.xapp.xjava.models.ReviewIdReq;
import com.xapp.xjava.models.ReviewReq;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.services.ReviewService;
import com.xapp.xjava.services.UsersService;

@RestController
@RequestMapping("/movies/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UsersRepository usersRepository;

	
    @Autowired
    private UsersService usersService;

	@PostMapping("")
	ResponseEntity<Review> addReview(@AuthenticationPrincipal CustomUserDetails user, @RequestBody ReviewReq req) throws Exception {
		String userName = user.getUsername();
        User userDetails = usersRepository.findByEmail(userName);
		Long userId = userDetails.getUserId();
		Review savedReview = reviewService.addReview(userId, req);
		return ResponseEntity.ok(savedReview);
	}

	@PutMapping("/{reviewId}") 
	ResponseEntity<Review> editReview(@PathVariable("reviewId") Long reviewId, @AuthenticationPrincipal CustomUserDetails user, @RequestBody EditReviewReq req ) throws Exception {
		String userName = user.getUsername();
		User userDetails = usersRepository.findByEmail(userName);
		Long userId = userDetails.getUserId();
		Review editedReview = reviewService.editReview(userId, reviewId, req);
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

	@DeleteMapping("")
	ResponseEntity<?> deleteReview(@AuthenticationPrincipal CustomUserDetails user, @RequestBody ReviewIdReq req ) {
		String userName = user.getUsername();
		User userDetails = usersRepository.findByEmail(userName);
		Long userId = userDetails.getUserId();
        reviewService.deleteReview(userId, req);
		return  ResponseEntity.noContent().build();
	}
}
