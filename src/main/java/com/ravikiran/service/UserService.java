package com.ravikiran.service;

import com.ravikiran.exception.UserException;
import com.ravikiran.modal.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
