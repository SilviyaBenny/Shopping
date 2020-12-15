package com.shopping.repository;

import java.util.List;

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

	private final String INSERT_QUERY = "INSERT INTO DEPARTMENT (DEPARTMENT_NAME,DESCRIPTION) VALUES (:DEPARTMENT_NAME,:DESCRIPTION)";
	private final String SELECT_ALL_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT ";
	private final String SELECT_BY_ID_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT WHERE ID = :ID";
	private final String UPDATE_QUERY = "UPDATE DEPARTMENT SET ID=:ID,DEPARTMENT_NAME=:DEPARTMENT_NAME, DESCRIPTION=:DESCRIPTION WHERE ID = :ID";
	private final String DELETE_QUERY = "DELETE FROM DEPARTMENT WHERE ID = :ID";

	public DepartmentDTO create(DepartmentDTO departmentDTO) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription());
		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters, keyHolder, new String[] { "ID" });
		departmentDTO.setId(keyHolder.getKey().intValue());
		return departmentDTO;
	}

	public List<DepartmentDTO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new DepartmentRowMapper());
	}

	public DepartmentDTO getById(int id) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new MapSqlParameterSource("ID", id),
				new DepartmentRowMapper());
	}

	public DepartmentDTO update(DepartmentDTO departmentDTO, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription());
		
		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		departmentDTO.setId(id);
		if(recordsUpdated!=1) {
			throw new DatabaseException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + id + " not found");
		}
		return departmentDTO;
	}

	public int deleteById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		 
	}
}
