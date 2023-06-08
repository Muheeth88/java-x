package com.xapp.xjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.Review;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.ReviewReq;
import com.xapp.xjava.repositories.ReviewRepository;
import com.xapp.xjava.repositories.UsersRepository;

@Service
public class ReviewService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	public ReviewRepository reviewRepository;
	
	public Review addReview(String userName, ReviewReq reviewrReq) {
		User userDetails = usersRepository.findByEmail(userName);
		Review review = new Review();
		review.setMovieId(reviewrReq.getMovieId());
		review.setTitle(reviewrReq.getTitle());
		review.setComment(reviewrReq.getComment());
		review.setUserId(userDetails.getUserId());
		return reviewRepository.save(review);
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
