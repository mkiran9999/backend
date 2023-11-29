package com.ravikiran.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravikiran.modal.Deal;
import com.ravikiran.modal.Product;

//DealRepository.java
public interface DealsRepository extends JpaRepository<Deal, Long> {

 Optional<Deal> findByProduct(Product product);

}

