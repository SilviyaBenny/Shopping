package com.shopping.botoresponse.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.responsejson.DepartmentResponseJson;

@Component
public class DepartmentBOtoResponseJsonMapper {
	
	public DepartmentResponseJson mapObject(DepartmentBO bo) {
		DepartmentResponseJson responseJson = new DepartmentResponseJson();
		responseJson.setId(bo.getId());
		responseJson.setDepartmentName(bo.getDepartmentName());
		responseJson.setDescription(bo.getDescription());
		
		return responseJson;
	}
}
