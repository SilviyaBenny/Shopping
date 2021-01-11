package com.shopping.BOtoResponse.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.responsejson.ProductResponseJson;

@Component
public class ProductBOtoResponseJsonMapper {
	
	public ProductResponseJson mapObject(ProductBO productBO) {
		ProductResponseJson json = new ProductResponseJson();
		json.setRecordId(productBO.getRecordId());
		json.setName(productBO.getName());
		json.setPrice(productBO.getPrice());
		json.setQuantity(productBO.getQuantity());
		json.setSku(productBO.getSku());
		json.setDepartmentId(productBO.getDepartmentId());
		json.setDescription(productBO.getDescription());
		return json;
	}
	
}
