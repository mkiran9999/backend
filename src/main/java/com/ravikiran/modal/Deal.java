package com.ravikiran.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Deal {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 @ManyToOne
 @JoinColumn(name = "product_id", nullable = false)
 private Product product;

 @Column(name = "expiry_time")
 private LocalDateTime expiryTime;

 // Constructors, getters, setters, etc.

 // Constructors, getters, setters, etc.
 public Deal() {
 }

 public Deal(Long id, Product product, LocalDateTime expiryTime) {
     this.id = id;
     this.product = product;
     this.expiryTime = expiryTime;
 }

 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public Product getProduct() {
     return product;
 }

 public void setProduct(Product product) {
     this.product = product;
 }

 public LocalDateTime getExpiryTime() {
     return expiryTime;
 }

 public void setExpiryTime(LocalDateTime expiryTime) {
     this.expiryTime = expiryTime;
 }
}

