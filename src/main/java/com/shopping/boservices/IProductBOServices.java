package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.ProductBO;
/**
 * 
 * Product BOService class
 *
 */
public interface IProductBOServices {

	/**
	 * 
	 * Create Product
	 * 
	 */
	public ProductBO create(ProductBO bo);

	/**
	 * 
	 * GetAll Product
	 * 
	 */
	public List<ProductBO> getAll();

	/**
	 * 
	 * Get Product ById
	 * 
	 */
	public ProductBO getById(int id);

	/**
	 * 
	 * Update Product
	 * 
	 */
	public ProductBO update(ProductBO bo, int id);

	/**
	 * 
	 * Delete Product
	 * 
	 */
	public int deleteById(int id);
}
