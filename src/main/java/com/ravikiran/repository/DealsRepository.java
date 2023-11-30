package com.ravikiran.repository;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravikiran.modal.Deal;
import com.ravikiran.modal.Product;

import java.util.List;

//DealRepository.java
public interface DealsRepository extends JpaRepository<Deal, Long> {

// Optional<Deal> findByProduct(Product product);
	
	Optional<Deal> findByProductId(Long productId);
 
	List<Deal> findByExpiryTimeBefore(ZonedDateTime expiryTime);

}

