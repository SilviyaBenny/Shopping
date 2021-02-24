package com.shopping.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.repository.dto.DepartmentDTO;
import com.shopping.rowmapper.DepartmentRowMapper;

@Repository
public class DepartmentDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO DEPARTMENT (RECORD_ID, DEPARTMENT_NAME,DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) VALUES (:RECORD_ID,:DEPARTMENT_NAME,:DESCRIPTION,:CREATED_BY,NOW(),:MODIFIED_BY,NOW())";
	private final String SELECT_ALL_QUERY = "SELECT RECORD_ID,DEPARTMENT_NAME, DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM DEPARTMENT ";
	private final String SELECT_BY_ID_QUERY = "SELECT RECORD_ID,DEPARTMENT_NAME, DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM DEPARTMENT WHERE RECORD_ID = :RECORD_ID";
	private final String UPDATE_QUERY = "UPDATE DEPARTMENT SET RECORD_ID=:RECORD_ID,DEPARTMENT_NAME=:DEPARTMENT_NAME, DESCRIPTION=:DESCRIPTION,MODIFIED_BY=:MODIFIED_BY,MODIFIED_DATE=:MODIFIED_DATE WHERE RECORD_ID = :RECORD_ID";
	private final String DELETE_QUERY = "DELETE FROM DEPARTMENT WHERE RECORD_ID = :RECORD_ID";

	public DepartmentDTO create(DepartmentDTO departmentDTO) {
		String recordId = UUID.randomUUID().toString();
		Date date = new Date();
		departmentDTO.setCreatedDate(date);
		departmentDTO.setModifiedDate(date);
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("RECORD_ID", recordId)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription())
				.addValue("CREATED_BY", departmentDTO.getCreatedBy())
				.addValue("CREATED_DATE", departmentDTO.getCreatedDate())
				.addValue("MODIFIED_BY", "testUser")
				.addValue("MODIFIED_DATE", departmentDTO.getModifiedDate());
		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters);
		departmentDTO.setRecordId(recordId);
		return departmentDTO;
	}

	public List<DepartmentDTO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new DepartmentRowMapper());
	}

	public DepartmentDTO getById(String recordId) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new MapSqlParameterSource("RECORD_ID", recordId),
				new DepartmentRowMapper());
	}

	public DepartmentDTO update(DepartmentDTO departmentDTO, String recordId) {
		
		departmentDTO.setModifiedDate(new Date());
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("RECORD_ID", recordId)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription())
				.addValue("MODIFIED_BY",departmentDTO.getModifiedBy())
				.addValue("MODIFIED_DATE", departmentDTO.getModifiedDate());;
		
		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		departmentDTO.setRecordId(recordId);
		if(recordsUpdated!=1) {
			throw new ItemNotFoundException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + recordId + " not found");
		}
		return departmentDTO;
	}

	public int deleteById(String recordId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("RECORD_ID", recordId);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		 
	}
}
