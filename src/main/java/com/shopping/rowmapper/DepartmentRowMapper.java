package com.shopping.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shopping.repository.dao.DepartmentDAO;

public class DepartmentRowMapper implements RowMapper<DepartmentDAO> {

	@Override
	public DepartmentDAO mapRow(ResultSet rs , int rowNum)throws SQLException{
		DepartmentDAO departmentDAO = new DepartmentDAO();
		departmentDAO.setId(rs.getInt("ID"));
		departmentDAO.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
		departmentDAO.setDescription(rs.getString("DESCRIPTION"));
		
		return departmentDAO;
	}
}
