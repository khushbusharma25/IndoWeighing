package com.ind.weighing.Indo_weighing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ind.weighing.Indo_weighing.domain.Cart;

public interface CartRepository extends MongoRepository<Cart, Long>{
	
}
