package com.xapp.xjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.Review;
import com.xapp.xjava.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	public ReviewRepository reviewRepository;
	
	public Review addReview(Review newReview) {
		return reviewRepository.save(newReview);
	}
	
	public List<Review> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll();
		return reviews;
	}
	
	public List<Review> getReviewsByMovie(Long movieId) {
		List<Review> movieReviews = reviewRepository.findByMovieId(movieId);
		return movieReviews;
	}
		
}
