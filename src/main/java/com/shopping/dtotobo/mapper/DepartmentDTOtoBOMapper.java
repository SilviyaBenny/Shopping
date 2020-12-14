package com.shopping.dtotobo.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.repository.dto.DepartmentDTO;

@Component
public class DepartmentDTOtoBOMapper {
	public DepartmentBO mapObject(DepartmentDTO dto) {
		DepartmentBO bo = new DepartmentBO();
		bo.setId(dto.getId());
		bo.setDepartmentName(dto.getDepartmentName());
		bo.setDescription(dto.getDescription());

		return bo;
	}
}
