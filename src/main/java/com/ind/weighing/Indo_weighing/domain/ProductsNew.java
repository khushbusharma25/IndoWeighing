/*package com.ind.weighing.Indo_weighing.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="products_new")
public class ProductsNew {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long pId;
		
	private String productName;
	
	private Double price;
	
	private Double weight;
	
	private String discription;
	
	private String productImgUrl;

	@ElementCollection
//	@CollectionTable( joinColumns= @JoinColumn(name="product_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductDetails> productDetails;


	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	
	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	
	
	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "ProductsNew [pId=" + pId + ", productName=" + productName + ", price=" + price + ", weight=" + weight
				+ ", discription=" + discription + ", productImgUrl=" + productImgUrl + ", productDetails="
				+ productDetails + "]";
	}
	
	
	
}
*/