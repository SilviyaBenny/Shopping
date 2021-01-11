package com.shopping.botodto.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.repository.dto.ProductDTO;

@Component
public class ProductBOtoDTOMapper {

	public ProductDTO mapObject(ProductBO productBO) {
		ProductDTO dto = new ProductDTO();
		dto.setRecordId(productBO.getRecordId());
		dto.setName(productBO.getName());
		dto.setPrice(productBO.getPrice());
		dto.setQuantity(productBO.getQuantity());
		dto.setSku(productBO.getSku());
		dto.setDepartmentId(productBO.getDepartmentId());
		dto.setDescription(productBO.getDescription());
		return dto;
	}
}
