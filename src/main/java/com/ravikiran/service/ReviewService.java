package com.ravikiran.service;

import java.util.List;

import com.ravikiran.exception.ProductException;
import com.ravikiran.modal.Review;
import com.ravikiran.modal.User;
import com.ravikiran.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
