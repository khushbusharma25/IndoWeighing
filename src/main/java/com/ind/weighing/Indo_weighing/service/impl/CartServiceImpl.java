package com.ind.weighing.Indo_weighing.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ind.weighing.Indo_weighing.domain.Cart;
import com.ind.weighing.Indo_weighing.repository.CartRepository;
import com.ind.weighing.Indo_weighing.service.CartService;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartRepository cartRepo;
	
	@Override
	public Map<String, Object> saveToCart(Integer pId, String username) {
		Map<String, Object> response= new HashMap<>();
		response.put("status", 2);
		response.put("message", "unable to add to cart");
		if(pId!=null) {
			Cart cart = new Cart();
			cart.setUserId(null);
			cart.setpId(pId);
			cartRepo.save(cart);
			response.put("status", 1);
			response.put("message", "product Added to cart");
		}
		return response;
	}
}
