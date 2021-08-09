package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.ProductBO;

public interface IProductBOServices {

	public ProductBO create(ProductBO bo);
	public List<ProductBO> getAll();
	public ProductBO getById(int id);
	public ProductBO update(ProductBO bo, int id);
	public int deleteById(int id);
}
