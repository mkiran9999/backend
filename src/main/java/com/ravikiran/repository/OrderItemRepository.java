package com.ravikiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravikiran.modal.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
