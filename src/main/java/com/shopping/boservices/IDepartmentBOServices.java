package com.shopping.boservices;

import java.util.List;

import com.shopping.bo.DepartmentBO;
/**
 * 
 * Department BOService class
 *
 */
public interface IDepartmentBOServices {

	/**
	 * 
	 * Create Department
	 * 
	 */
	public DepartmentBO create(DepartmentBO bo);

	/**
	 * 
	 * GetAll Department
	 * 
	 */
	public List<DepartmentBO> getAll();

	/**
	 * 
	 * Get Department ById
	 * 
	 */
	public DepartmentBO getById(int id);

	/**
	 * 
	 * Update Department
	 * 
	 */
	public DepartmentBO update(DepartmentBO departmentBO, int id);

	/**
	 * 
	 * Delete Department
	 * 
	 */
	public int deleteById(int id);
}
