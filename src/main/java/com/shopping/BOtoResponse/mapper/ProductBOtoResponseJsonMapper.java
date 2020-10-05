package com.shopping.botoresponse.mapper;

import com.shopping.bo.ProductBO;
import com.shopping.responsejson.ProductResponseJson;

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
