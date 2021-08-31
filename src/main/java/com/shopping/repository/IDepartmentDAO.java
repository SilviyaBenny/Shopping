package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dto.DepartmentDTO;
/**
 * 
 * Department CRUD operations
 *
 */
public interface IDepartmentDAO {

	/**
	 * Create Department
	 * 
	 * 
	 */
	public DepartmentDTO create(DepartmentDTO departmentDTO);

	/**
	 * GetAll Department
	 * 
	 * 
	 */
	public List<DepartmentDTO> getAll();

	/**
	 * Get Department ById
	 * 
	 * 
	 */
	public DepartmentDTO getById(int id);

	/**
	 * Update Department
	 * 
	 * 
	 */
	public DepartmentDTO update(DepartmentDTO departmentDTO, int id);

	/**
	 * Delete Department
	 * 
	 * 
	 */
	public int deleteById(int id);
}
