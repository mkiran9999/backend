package com.ravikiran.service;

import com.ravikiran.exception.CartItemException;
import com.ravikiran.exception.UserException;
import com.ravikiran.modal.Cart;
import com.ravikiran.modal.CartItem;
import com.ravikiran.modal.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
