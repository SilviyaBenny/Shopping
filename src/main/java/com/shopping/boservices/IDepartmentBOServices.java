package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.DepartmentBO;

public interface IDepartmentBOServices {

	public DepartmentBO create(DepartmentBO bo);
	public List<DepartmentBO> getAll();
	public DepartmentBO getById(String recordId);
	public DepartmentBO update(DepartmentBO departmentBO, String recordId);
	public int deleteById(String recordId);
}
