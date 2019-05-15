package com.ind.weighing.Indo_weighing.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ProductDetails {

	@Id
	private Long id;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="sell_per_month")
	private Long sellPerMonth;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCellPerMonth() {
		return sellPerMonth;
	}

	public void setCellPerMonth(Long cellPerMonth) {
		this.sellPerMonth = cellPerMonth;
	}

	
	

	@Override
	public String toString() {
		return "ProductDetails [id="  + ", quantity=" + quantity + ", cellPerMonth=" + sellPerMonth + ", products="
				 + "]";
	}
	
	
	
	
	
}
