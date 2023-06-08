package com.xapp.xjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.xapp.xjava.entities.Review;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.EditReviewReq;
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

	public Review editReview(String userName, Long reviewId, EditReviewReq req) throws Exception {
		User userDetails = usersRepository.findByEmail(userName);
		Optional<Review> currReviewOp = getReviewByReviewId(reviewId);
		try {
			if(currReviewOp.isPresent()) {
				Review currReview = currReviewOp.get();
				if(userDetails.getUserId() == currReview.getUserId()) {
					currReview.setTitle(req.getTitle());
					currReview.setComment(req.getComment());
					reviewRepository.save(currReview);
				} else {
					System.out.println("Not Your comment!!!");
					return new Review();
				}
			} else {
				System.out.println("Review Not Found!");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
		return null;
	}
	
	public List<Review> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll();
		return reviews;
	}
	
	public List<Review> getReviewsByMovie(Long movieId) {
		List<Review> movieReviews = reviewRepository.findByMovieId(movieId);
		return movieReviews;
	}

	public Optional<Review> getReviewByReviewId(Long reviewId) {
		Optional<Review> review = reviewRepository.findById(reviewId);
		return review;
	}


		
}
