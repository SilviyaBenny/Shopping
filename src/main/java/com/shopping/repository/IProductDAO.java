package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dto.ProductDTO;
/**
 * 
 * Product CRUD operations
 *
 */
public interface IProductDAO {

	/**
	 * Create Product
	 * 
	 * 
	 */
	public ProductDTO create(ProductDTO productDTO);

	/**
	 * GetAll Product
	 * 
	 * 
	 */
	public List<ProductDTO> getAll();

	/**
	 * Get Product ById
	 * 
	 * 
	 */
	public ProductDTO getById(int id);

	/**
	 * Update Product
	 * 
	 * 
	 */
	public ProductDTO update(ProductDTO productDTO, int id);

	/**
	 * Delete Product
	 * 
	 * 
	 */
	public int deleteById(int id);
}
