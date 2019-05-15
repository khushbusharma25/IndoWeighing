package com.ind.weighing.Indo_weighing.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cart")
public class Cart {

	private String id;
	
	private Integer userId;
	
	private int pId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", pId=" + pId + "]";
	}
	
	
}
