package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.DepartmentBO;

public interface IDepartmentBOServices {

	public DepartmentBO create(DepartmentBO bo);
	public List<DepartmentBO> getAll();
	public DepartmentBO getById(int id);
	public DepartmentBO update(DepartmentBO departmentBO, int id);
	public int deleteById(int id);
}
