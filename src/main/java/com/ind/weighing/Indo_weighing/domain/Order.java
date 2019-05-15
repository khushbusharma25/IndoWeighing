package com.ind.weighing.Indo_weighing.domain;

public class Order {

	private Long id;
	
	private Long customerId;
	
	private Long productId;
	
	private String delivaryAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDelivaryAddress() {
		return delivaryAddress;
	}

	public void setDelivaryAddress(String delivaryAddress) {
		this.delivaryAddress = delivaryAddress;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", delivaryAddress="
				+ delivaryAddress + "]";
	}
	
	
}
