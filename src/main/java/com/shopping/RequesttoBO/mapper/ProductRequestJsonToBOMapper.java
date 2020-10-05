package com.shopping.requesttobo.mapper;

import com.shopping.bo.ProductBO;
import com.shopping.requestjson.ProductRequestJson;

public class ProductRequestJsonToBOMapper {

	public ProductBO mapObject(ProductRequestJson requestJson) {
		ProductBO bo = new ProductBO();
		bo.setId(requestJson.getId());
		bo.setName(requestJson.getName());
		bo.setPrice(requestJson.getPrice());
		bo.setQuantity(requestJson.getQuantity());
		bo.setSku(requestJson.getSku());

		return bo;
	}
}
