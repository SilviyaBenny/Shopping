package com.shopping.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.shopping.exception.DatabaseException;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.repository.dto.DepartmentDTO;
import com.shopping.rowmapper.DepartmentRowMapper;

@Repository
public class DepartmentDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO DEPARTMENT (RECORD_ID, DEPARTMENT_NAME,DESCRIPTION) VALUES (:RECORD_ID,:DEPARTMENT_NAME,:DESCRIPTION)";
	private final String SELECT_ALL_QUERY = "SELECT RECORD_ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT ";
	private final String SELECT_BY_ID_QUERY = "SELECT RECORD_ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT WHERE RECORD_ID = :RECORD_ID";
	private final String UPDATE_QUERY = "UPDATE DEPARTMENT SET RECORD_ID=:RECORD_ID,DEPARTMENT_NAME=:DEPARTMENT_NAME, DESCRIPTION=:DESCRIPTION WHERE RECORD_ID = :RECORD_ID";
	private final String DELETE_QUERY = "DELETE FROM DEPARTMENT WHERE RECORD_ID = :RECORD_ID";

	public DepartmentDTO create(DepartmentDTO departmentDTO) {
		String recordId = UUID.randomUUID().toString();
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("RECORD_ID", recordId)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription());
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
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("RECORD_ID", recordId)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription());
		
		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		departmentDTO.setRecordId(recordId);
		if(recordsUpdated!=1) {
			throw new DatabaseException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + recordId + " not found");
		}
		return departmentDTO;
	}

	public int deleteById(String recordId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("RECORD_ID", recordId);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		 
	}
}
