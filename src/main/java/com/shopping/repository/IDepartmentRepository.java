package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dao.DepartmentDAO;

public interface IDepartmentRepository {

	public DepartmentDAO create(DepartmentDAO departmentDAO);
	public List<DepartmentDAO> getAll();
	public DepartmentDAO getById(int id);
	public DepartmentDAO update(DepartmentDAO departmentDAO, int id);
	public int deleteById(int id);
}
