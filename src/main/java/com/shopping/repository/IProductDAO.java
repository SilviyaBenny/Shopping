package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dto.ProductDTO;

public interface IProductDAO {

	public ProductDTO create(ProductDTO productDTO);

	public List<ProductDTO> getAll();

	public ProductDTO getById(int id);

	public ProductDTO update(ProductDTO productDTO, int id);

	public int deleteById(int id);
}
