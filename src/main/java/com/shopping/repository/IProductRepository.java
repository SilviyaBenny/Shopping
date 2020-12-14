package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dao.ProductDAO;

public interface IProductRepository {

	public ProductDAO create(ProductDAO productDAO);
	public List<ProductDAO> getAll();
	public ProductDAO getById(int id);
	public ProductDAO update(ProductDAO productDAO, int id);
	public int deleteById(int id);
}
