package com.shopping.botodto.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.repository.dto.DepartmentDTO;

@Component
public class DepartmentBOtoDTOMapper {

	public DepartmentDTO mapObject(DepartmentBO bo) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(bo.getId());
		dto.setDepartmentName(bo.getDepartmentName());
		dto.setDescription(bo.getDescription());

		return dto;
	}
}
