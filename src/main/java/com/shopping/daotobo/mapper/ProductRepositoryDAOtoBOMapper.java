package com.shopping.daotobo.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.repository.dao.ProductDAO;

@Component
public class ProductRepositoryDAOtoBOMapper {

	public ProductBO mapObject(ProductDAO repositoryDAO) {
		ProductBO productBO = new ProductBO();
		productBO.setId(repositoryDAO.getId());
		productBO.setName(repositoryDAO.getName());
		productBO.setPrice(repositoryDAO.getPrice());
		productBO.setQuantity(repositoryDAO.getQuantity());
		productBO.setSku(repositoryDAO.getSku());
		productBO.setDepartmentId(repositoryDAO.getDepartmentId());
		return productBO;
	}
}
