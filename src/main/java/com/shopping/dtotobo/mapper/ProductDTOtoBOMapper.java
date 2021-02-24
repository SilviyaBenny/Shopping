package com.shopping.dtotobo.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.repository.dto.ProductDTO;

@Component
public class ProductDTOtoBOMapper {

	public ProductBO mapObject(ProductDTO dto) {
		ProductBO productBO = new ProductBO();
		productBO.setRecordId(dto.getRecordId());
		productBO.setName(dto.getName());
		productBO.setPrice(dto.getPrice());
		productBO.setQuantity(dto.getQuantity());
		productBO.setSku(dto.getSku());
		productBO.setDepartmentId(dto.getDepartmentId());
		productBO.setDescription(dto.getDescription());
		productBO.setCreatedBy(dto.getCreatedBy());
		productBO.setCreatedDate(dto.getCreatedDate());
		productBO.setModifiedBy(dto.getModifiedBy());
		productBO.setModifiedDate(dto.getModifiedDate());
		return productBO;
	}
}
