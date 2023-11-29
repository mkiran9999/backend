package com.ravikiran.service;

import java.util.List;

import com.ravikiran.exception.ProductException;
import com.ravikiran.modal.Rating;
import com.ravikiran.modal.User;
import com.ravikiran.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
