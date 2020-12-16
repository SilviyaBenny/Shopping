package com.shopping.requesttobomapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.requestjson.ProductRequestJson;

@Component
public class ProductRequestJsonToBOMapper {

	public ProductBO mapObject(ProductRequestJson requestJson) {
		ProductBO bo = new ProductBO();
		bo.setId(requestJson.getId());
		bo.setName(requestJson.getName());
		bo.setPrice(requestJson.getPrice());
		bo.setQuantity(requestJson.getQuantity());
		bo.setSku(requestJson.getSku());
		bo.setDepartmentId(requestJson.getDepartmentId());
		bo.setDescription(requestJson.getDescription());
		return bo;
	}
}
