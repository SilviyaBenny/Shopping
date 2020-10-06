package com.shopping.botoresponse.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.responsejson.ProductResponseJson;

@Component
public class ProductBOtoResponseJsonMapper {
	
	public ProductResponseJson mapObject(ProductBO productBO) {
		ProductResponseJson json = new ProductResponseJson();
		json.setId(productBO.getId());
		json.setName(productBO.getName());
		json.setPrice(productBO.getPrice());
		json.setQuantity(productBO.getQuantity());
		json.setSku(productBO.getSku());
		
		return json;
	}
}
