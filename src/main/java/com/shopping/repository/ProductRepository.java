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
import com.shopping.repository.dao.ProductDAO;
import com.shopping.rowmapper.ProductRowMapper;

@Repository
public class ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO PRODUCT (NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID) VALUES (:NAME,:QUANTITY,:PRICE,:SKU,:DEPARTMENT_ID)";
	private final String SELECT_ALL_QUERY = "SELECT ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID FROM PRODUCT ";
	private final String SELECT_BY_ID_QUERY = "SELECT ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID FROM PRODUCT WHERE ID = :ID";
	private final String UPDATE_QUERY = "UPDATE PRODUCT SET ID=:ID,NAME=:NAME, QUANTITY=:QUANTITY,PRICE=:PRICE,SKU=:SKU,DEPARTMENT_ID=:DEPARTMENT_ID  WHERE ID = :ID";
	private final String DELETE_QUERY = "DELETE FROM PRODUCT WHERE ID = :ID";

	public ProductDAO create(ProductDAO productDAO) {
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("NAME", productDAO.getName()).addValue("QUANTITY", productDAO.getQuantity())
				.addValue("PRICE", productDAO.getPrice()).addValue("SKU", productDAO.getSku())
				.addValue("DEPARTMENT_ID", productDAO.getDepartmentId());

		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters, keyHolder, new String[] { "ID" });
		productDAO.setId(keyHolder.getKey().intValue());
		return productDAO;
	}

	public List<ProductDAO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new ProductRowMapper());
	}

	public ProductDAO getById(int id) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new MapSqlParameterSource("ID", id),
				new ProductRowMapper());
	}

	public ProductDAO update(ProductDAO productDAO, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id)
				.addValue("NAME", productDAO.getName()).addValue("QUANTITY", productDAO.getQuantity())
				.addValue("PRICE", productDAO.getPrice()).addValue("SKU", productDAO.getSku())
				.addValue("DEPARTMENT_ID", productDAO.getDepartmentId());

		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		productDAO.setId(id);
		if(recordsUpdated!=1) {
			throw new DatabaseException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + id + " not found");
		}
		return productDAO;
	}

	public int deleteById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
	}
}