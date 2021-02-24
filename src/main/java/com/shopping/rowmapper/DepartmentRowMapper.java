package com.shopping.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shopping.repository.dto.DepartmentDTO;

public class DepartmentRowMapper implements RowMapper<DepartmentDTO> {

	@Override
	public DepartmentDTO mapRow(ResultSet rs , int rowNum)throws SQLException{
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setRecordId(rs.getString("RECORD_ID"));
		departmentDTO.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
		departmentDTO.setDescription(rs.getString("DESCRIPTION"));
		departmentDTO.setCreatedBy(rs.getString("CREATED_BY"));
		departmentDTO.setCreatedDate(rs.getDate("CREATED_DATE"));
		departmentDTO.setModifiedBy(rs.getString("MODIFIED_BY"));
		departmentDTO.setModifiedDate(rs.getDate("MODIFIED_DATE"));
		
		return departmentDTO;
	}
}
