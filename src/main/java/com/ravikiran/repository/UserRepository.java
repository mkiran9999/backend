package com.ravikiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravikiran.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
