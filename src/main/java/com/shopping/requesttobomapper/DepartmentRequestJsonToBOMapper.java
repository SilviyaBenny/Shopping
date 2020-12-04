package com.shopping.requesttobomapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.requestjson.DepartmentRequestJson;

@Component
public class DepartmentRequestJsonToBOMapper {

	public DepartmentBO mapObject(DepartmentRequestJson requestJson) {
		DepartmentBO bo = new DepartmentBO();
		bo.setId(requestJson.getId());
		bo.setDepartmentName(requestJson.getDepartmentName());
		bo.setDescription(requestJson.getDescription());

		return bo;
	}
}
