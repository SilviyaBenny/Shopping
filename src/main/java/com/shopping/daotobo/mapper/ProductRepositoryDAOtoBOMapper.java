package com.shopping.daotobo.mapper;

import com.shopping.bo.ProductBO;
import com.shopping.repositroy.dao.ProductDAO;

public class ProductRepositoryDAOtoBOMapper {

	public ProductBO mapObject(ProductDAO repositoryDAO) {
		ProductBO productBO = new ProductBO();
		productBO.setId(repositoryDAO.getId());
		productBO.setName(repositoryDAO.getName());
		productBO.setPrice(repositoryDAO.getPrice());
		productBO.setQuantity(repositoryDAO.getQuantity());
		productBO.setSku(repositoryDAO.getSku());

		return productBO;
	}
}
