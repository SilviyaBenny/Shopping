package com.shopping.botodao.mapper;

import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.repository.dao.DepartmentDAO;

@Component
public class DepartmentBOtoRepositoryDAOMapper {

	public DepartmentDAO mapObject(DepartmentBO bo) {
		DepartmentDAO dao = new DepartmentDAO();
		dao.setId(bo.getId());
		dao.setDepartmentName(bo.getDepartmentName());
		dao.setDescription(bo.getDescription());

		return dao;
	}
}
