package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.ProductBO;

public interface IProductBOServices {

	public ProductBO create(ProductBO bo);
	public List<ProductBO> getAll();
	public ProductBO getById(String recordId);
	public ProductBO update(ProductBO bo, String recordId);
	public int deleteById(String recordId);
}
