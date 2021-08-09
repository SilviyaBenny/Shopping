package com.shopping.mapper.dtotobo;

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
		bo.setCreatedBy(dto.getCreatedBy());
		bo.setCreatedDate(dto.getCreatedDate());
		bo.setModifiedBy(dto.getModifiedBy());
		bo.setModifiedDate(dto.getModifiedDate());

		return bo;
	}
}
