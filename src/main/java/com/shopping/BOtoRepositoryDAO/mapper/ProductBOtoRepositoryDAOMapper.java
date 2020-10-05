package com.shopping.BOtoRepositoryDAO.mapper;

import com.shopping.BO.ProductBO;
import com.shopping.repositroy.dao.ProductDAO;

public class ProductBOtoRepositoryDAOMapper {

	public ProductDAO mapObject(ProductBO productBO) {
		ProductDAO dao = new ProductDAO();
		dao.setId(productBO.getId());
		dao.setName(productBO.getName());
		dao.setPrice(productBO.getPrice());
		dao.setQuantity(productBO.getQuantity());
		dao.setSku(productBO.getSku());

		return dao;
	}
}
