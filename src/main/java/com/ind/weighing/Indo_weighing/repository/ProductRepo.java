package com.ind.weighing.Indo_weighing.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.ind.weighing.Indo_weighing.domain.Products;




public interface ProductRepo extends MongoRepository<Products, Long> {
	@Query(value="{$and:[{'productName':?0},{'pId':{$ne:?1}}]}", count=true)
	Long isProductExist(String productName, int pId);
/*
	@Query(nativeQuery=true,value="SELECT COUNT(*) \r\n" + 
			"FROM  products \r\n" + 
			"where product_name = :productName and not pId=:pId ")
	int isProductExist(String productName, Long pId);*/

	Optional<Products> findByPId(int getpId);

}
