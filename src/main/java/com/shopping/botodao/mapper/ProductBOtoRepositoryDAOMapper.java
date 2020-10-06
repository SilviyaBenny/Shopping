package com.shopping.botodao.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.repository.dao.ProductDAO;

@Component
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
