package com.shopping.daotobo.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.repository.dao.DepartmentDAO;

@Component
public class DepartmentRepositoryDAOtoBOMapper {
	public DepartmentBO mapObject(DepartmentDAO dao) {
		DepartmentBO bo = new DepartmentBO();
		bo.setId(dao.getId());
		bo.setDepartmentName(dao.getDepartmentName());
		bo.setDescription(dao.getDescription());

		return bo;
	}
}
