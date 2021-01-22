package com.shopping.BOtoResponse.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.responsejson.DepartmentResponseJson;

@Component
public class DepartmentBOtoResponseJsonMapper {
	
	public DepartmentResponseJson mapObject(DepartmentBO bo) {
		DepartmentResponseJson responseJson = new DepartmentResponseJson();
		responseJson.setRecordId(bo.getRecordId());
		responseJson.setDepartmentName(bo.getDepartmentName());
		responseJson.setDescription(bo.getDescription());
		responseJson.setCreatedBy(bo.getCreatedBy());
		responseJson.setCreatedDate(bo.getCreatedDate());
		responseJson.setModifiedBy(bo.getModifiedBy());
		responseJson.setModifiedDate(bo.getModifiedDate());
		
		return responseJson;
	}
}
