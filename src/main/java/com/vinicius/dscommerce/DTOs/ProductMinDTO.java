package com.vinicius.dscommerce.DTOs;

import com.vinicius.dscommerce.projections.ProductProjection;

public class ProductMinDTO {
	
	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
	
	public ProductMinDTO() {
	}
	
	public ProductMinDTO(ProductProjection projection) {
		id = projection.getId();
		name = projection.getName();
		price= projection.getPrice();
		imgUrl = projection.getImgUrl();
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
