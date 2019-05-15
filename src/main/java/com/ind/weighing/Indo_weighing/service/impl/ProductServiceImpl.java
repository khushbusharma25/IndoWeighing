package com.ind.weighing.Indo_weighing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ind.weighing.Indo_weighing.domain.Products;
//import com.ind.weighing.Indo_weighing.domain.ProductsNew;
import com.ind.weighing.Indo_weighing.repository.ProductRepo;
import com.ind.weighing.Indo_weighing.service.ProductService;
import com.ind.weighing.Indo_weighing.service.SequenceService;



@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	SequenceService sequenceService;
	
	/*@Autowired
	ProductsNewRepository productsNewRepository;*/
	
	@Override
	public ResponseEntity<?> getAllProducts() {
		List<Products> productList=productRepo.findAll();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@Override
	public Products getProductById(Long pId) {
		return productRepo.findById(pId).get();
	}

	@Override
	public Map<String, Object> saveProduct(Products products) {
		Map<String, Object> response= new HashMap<>();
		Long count = productRepo.isProductExist(products.getProductName(),products.getpId());
		try {
			if(count>=1) {
				response.put("status", 2);
				response.put("message", "product already exist");
			}
			else {
				if(products.getpId()==0) {
					products.setpId(sequenceService.getNextSequence("customSequences"));
					productRepo.save(products);
				}else {
					Optional<Products> productsOptional = productRepo.findByPId(products.getpId());
					Products products2 = productsOptional.get();
					products2.setPrice(products.getPrice());
					products2.setDiscription(products.getDiscription());
					products2.setProductName(products.getProductName());
					products2.setWeight(products.getWeight());
					productRepo.save(products2);
				}
				response.put("status", 1);
				response.put("message", "product saved successsfully");
			}
		} catch (Exception e) {
			response.put("status", 2);
			response.put("message", "All fiels are required");
		}
		 return response;
	}

	

	@Override
	public Map<String, Object> deleteProduct(Long pId) {
		Map<String, Object> response= new HashMap<>();
		response.put("status", 2);
		response.put("message", "unable to delete");
		if(pId!=null) {
			productRepo.deleteById(pId);
			response.put("status", 1);
			response.put("message", "product deleted");
		}
		return response;
	}

	
	/*	@Override
	public ResponseEntity<?> getAllProductsNew() {
		List<ProductsNew> productList=(List<ProductsNew>) productsNewRepository.findAll();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@Override
	public ProductsNew getProductByIdNew(Long pId) {
		return productsNewRepository.findById(pId).get();
	}

	@Override
	public void saveProductNew(ProductsNew products) {
		productsNewRepository.save(products);
	}

	@Override
	public Map<String, Object> deleteProductNew(Long pId) {
		Map<String, Object> response= new HashMap<>();
		response.put("status", 2);
		response.put("message", "unable to delete");
		if(pId!=null) {
			productsNewRepository.deleteById(pId);
			response.put("status", 1);
			response.put("message", "product deleted");
		}
		return response;
	}*/

	

}
