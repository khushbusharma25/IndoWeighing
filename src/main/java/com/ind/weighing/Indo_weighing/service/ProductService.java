package com.ind.weighing.Indo_weighing.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ind.weighing.Indo_weighing.domain.Products;
//import com.ind.weighing.Indo_weighing.domain.ProductsNew;


public interface ProductService {

	ResponseEntity<?> getAllProducts();

	Products getProductById(Long pId);

	Map<String, Object> saveProduct(Products products);

	Map<String, Object> deleteProduct(Long pId);

/*	ResponseEntity<?> getAllProductsNew();

	void saveProductNew(ProductsNew products);

	ProductsNew getProductByIdNew(Long pId);

	Map<String, Object> deleteProductNew(Long pId);*/

}
