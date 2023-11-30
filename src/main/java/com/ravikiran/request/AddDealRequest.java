package com.ravikiran.request;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;

public class AddDealRequest {
	
	private Long productId;
//	private LocalDateTime expiryTime;
	private ZonedDateTime expiryTime;
    private int discountPercent;
    private double price;
    private double discountedPrice;
    public int getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public ZonedDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(ZonedDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	
//	public LocalDateTime getExpiryTime() {
//		return expiryTime;
//	}
//	public void setExpiryTime(LocalDateTime expiryTime) {
//		this.expiryTime = expiryTime;
//	}

   
}
