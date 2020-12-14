package com.shopping.repository;

import java.util.List;

import com.shopping.repository.dto.DepartmentDTO;

public interface IDepartmentDAO {

	public DepartmentDTO create(DepartmentDTO departmentDTO);
	public List<DepartmentDTO> getAll();
	public DepartmentDTO getById(int id);
	public DepartmentDTO update(DepartmentDTO departmentDTO, int id);
	public int deleteById(int id);
}
