package com.ind.weighing.Indo_weighing.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ind.weighing.Indo_weighing.domain.Products;
//import com.ind.weighing.Indo_weighing.domain.ProductsNew;
import com.ind.weighing.Indo_weighing.service.CartService;
import com.ind.weighing.Indo_weighing.service.ProductService;
import com.ind.weighing.Indo_weighing.utils.JWTUtils;



@RestController
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private JWTUtils jWTUtils;
	
	@RequestMapping(value="/getAllProducts", method=RequestMethod.GET)
	public ResponseEntity<?> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping(value="/getProductsById", method=RequestMethod.GET)
	public Products getProductById(Long pId){
		return productService.getProductById(pId);
	}
	@RequestMapping(value="/saveProducts", method=RequestMethod.POST)
	public Map<String, Object> saveProduct(@RequestBody Products products){
		
		return productService.saveProduct(products);
	}
	
	public Map<String, Object> deleteProduct(Long pId){
		return productService.deleteProduct(pId);
	}
	
	@RequestMapping(value="/saveToCart", method=RequestMethod.GET)
	public Map<String, Object> saveToCart(HttpServletRequest request ,Integer pId){
		String token = request.getHeader(tokenHeader);
    	String username = jWTUtils.getUsernameFromToken(token);
		System.out.println("controller");
		return cartService.saveToCart(pId,username);
	}
	
	@RequestMapping(value="/getAllProductsNew", method=RequestMethod.GET)
	public ResponseEntity<?> getAllProductsNew(){
		return productService.getAllProducts();
	}
	
	/*public ProductsNew getProductByIdNew(Long pId){
		return productService.getProductByIdNew(pId);
	}
	@RequestMapping(value="/saveProductsNew", method=RequestMethod.POST)
	public void saveProductNew(@RequestBody ProductsNew products){
		
		 productService.saveProductNew(products);
	}
	
	@RequestMapping(value="/saveProductsNew", method=RequestMethod.POST)
	public Map<String, Object> deleteProductNew(@RequestBody ProductsNew products){
		return productService.deleteProductNew(products.getpId());
	}*/
	
	
	
}
