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
import com.shopping.repository.dao.DepartmentDAO;
import com.shopping.rowmapper.DepartmentRowMapper;

@Repository
public class DepartmentRepository {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO DEPARTMENT (DEPARTMENT_NAME,DESCRIPTION) VALUES (:DEPARTMENT_NAME,:DESCRIPTION)";
	private final String SELECT_ALL_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT ";
	private final String SELECT_BY_ID_QUERY = "SELECT ID,DEPARTMENT_NAME, DESCRIPTION FROM DEPARTMENT WHERE ID = :ID";
	private final String UPDATE_QUERY = "UPDATE DEPARTMENT SET ID=:ID,DEPARTMENT_NAME=:DEPARTMENT_NAME, DESCRIPTION=:DESCRIPTION WHERE ID = :ID";
	private final String DELETE_QUERY = "DELETE FROM DEPARTMENT WHERE ID = :ID";

	public DepartmentDAO create(DepartmentDAO departmentDAO) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("DEPARTMENT_NAME", departmentDAO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDAO.getDescription());
		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters, keyHolder, new String[] { "ID" });
		departmentDAO.setId(keyHolder.getKey().intValue());
		return departmentDAO;
	}

	public List<DepartmentDAO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new DepartmentRowMapper());
	}

	public DepartmentDAO getById(int id) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new MapSqlParameterSource("ID", id),
				new DepartmentRowMapper());
	}

	public DepartmentDAO update(DepartmentDAO departmentDAO, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id)
				.addValue("DEPARTMENT_NAME", departmentDAO.getDepartmentName())
				.addValue("DESCRIPTION", departmentDAO.getDescription());
		
		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		departmentDAO.setId(id);
		if(recordsUpdated!=1) {
			throw new DatabaseException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + id + " not found");
		}
		return departmentDAO;
	}

	public int deleteById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		 
	}
}
