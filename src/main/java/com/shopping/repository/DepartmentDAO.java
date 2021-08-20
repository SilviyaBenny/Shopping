package com.shopping.repository;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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

	private static final String INSERT_QUERY = "INSERT INTO DEPARTMENT (DEPARTMENT_NAME,DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) VALUES (:DEPARTMENT_NAME,:DESCRIPTION,:CREATED_BY,NOW(),:MODIFIED_BY,NOW())";
	private static final String SELECT_ALL_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM DEPARTMENT ";
	private static final String SELECT_BY_ID_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM DEPARTMENT WHERE ID = :ID";
	private static final String UPDATE_QUERY = "UPDATE DEPARTMENT SET DEPARTMENT_NAME=:DEPARTMENT_NAME, DESCRIPTION=:DESCRIPTION,MODIFIED_BY=:MODIFIED_BY,MODIFIED_DATE=:MODIFIED_DATE WHERE ID = :ID";
	private static final String DELETE_QUERY = "DELETE FROM DEPARTMENT WHERE ID = :ID";

	public DepartmentDTO create(DepartmentDTO departmentDTO) {

		Date date = new Date();
		departmentDTO.setCreatedDate(date);
		departmentDTO.setModifiedDate(date);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new MapSqlParameterSource()

				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription())
				.addValue("CREATED_BY", departmentDTO.getCreatedBy())
				.addValue("CREATED_DATE", departmentDTO.getCreatedDate()).addValue("MODIFIED_BY", "testUser")
				.addValue("MODIFIED_DATE", departmentDTO.getModifiedDate());
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

		departmentDTO.setModifiedDate(new Date());
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id)
				.addValue("DEPARTMENT_NAME", departmentDTO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDTO.getDescription())
				.addValue("MODIFIED_BY", departmentDTO.getModifiedBy())
				.addValue("MODIFIED_DATE", departmentDTO.getModifiedDate());
		;

		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		departmentDTO.setId(id);
		if (recordsUpdated != 1) {
			throw new ItemNotFoundException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + id + " not found");
		}
		return departmentDTO;
	}

	public int deleteById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);

	}
}
