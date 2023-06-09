package com.xapp.xjava.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.xapp.xjava.entities.Review;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.EditReviewReq;
import com.xapp.xjava.models.ReviewIdReq;
import com.xapp.xjava.models.ReviewReq;
import com.xapp.xjava.repositories.ReviewRepository;
import com.xapp.xjava.repositories.UsersRepository;

@Service
public class ReviewService {

	@Autowired
	public ReviewRepository reviewRepository;

	public Review addReview(String userName, Long userId, ReviewReq reviewrReq) throws Exception {
		try {
			Review review = new Review();
			review.setReviewBy(userName);
			review.setMovieId(reviewrReq.getMovieId());
			review.setTitle(reviewrReq.getTitle());
			review.setComment(reviewrReq.getComment());
			review.setUserId(userId);
			review.setReviewTime(ZonedDateTime.now());
			return reviewRepository.save(review);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Review editReview(Long userId, Long reviewId, EditReviewReq req) throws Exception {

		Optional<Review> currReviewOp = getReviewByReviewId(reviewId);
		Review currReview = currReviewOp.get();
		try {
			if (userId == currReview.getUserId()) {
				currReview.setTitle(req.getTitle());
				currReview.setComment(req.getComment());
				reviewRepository.save(currReview);
			} else {
				System.out.println("Not Your comment!!!");
				return new Review();
			}
		} catch (Exception e) { 
			System.out.println("Something went wrong");
		}
		return null;
	}

	public Review deleteReview(Long userId, Long reviewId) {

		try {
			Optional<Review> currReviewOp = getReviewByReviewId(reviewId);
			Review currReview = currReviewOp.get();
			try {
				if (userId == currReview.getUserId()) {
					reviewRepository.deleteById(reviewId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Something Went Wrong In Service");
			}
		} catch (Exception e) {
				System.out.println("Something Went Wrong In Optional");
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
