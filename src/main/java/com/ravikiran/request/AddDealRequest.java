package com.ravikiran.request;

import java.time.LocalDateTime;

public class AddDealRequest {
	private Long productId;
    public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	private LocalDateTime expiryTime;

   
}
