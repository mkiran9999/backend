package com.ravikiran.service;

import com.ravikiran.exception.ProductException;
import com.ravikiran.modal.Cart;
import com.ravikiran.modal.CartItem;
import com.ravikiran.modal.User;
import com.ravikiran.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
