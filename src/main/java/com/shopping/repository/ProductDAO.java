package com.shopping.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.shopping.exception.DatabaseException;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.repository.dto.ProductDTO;
import com.shopping.rowmapper.ProductRowMapper;

@Repository
public class ProductDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO PRODUCT (RECORD_ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION) VALUES (:RECORD_ID,:NAME,:QUANTITY,:PRICE,:SKU,:DEPARTMENT_ID,:DESCRIPTION)";
	private final String SELECT_ALL_QUERY = "SELECT RECORD_ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION FROM PRODUCT ";
	private final String SELECT_BY_ID_QUERY = "SELECT RECORD_ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION FROM PRODUCT WHERE RECORD_ID = :RECORD_ID";
	private final String UPDATE_QUERY = "UPDATE PRODUCT SET RECORD_ID=:RECORD_ID,NAME=:NAME, QUANTITY=:QUANTITY,PRICE=:PRICE,SKU=:SKU,DEPARTMENT_ID=:DEPARTMENT_ID,DESCRIPTION=:DESCRIPTION  WHERE RECORD_ID = :RECORD_ID";
	private final String DELETE_QUERY = "DELETE FROM PRODUCT WHERE RECORD_ID = :RECORD_ID";

	public ProductDTO create(ProductDTO productDTO) {
		String recordId = UUID.randomUUID().toString();
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("RECORD_ID", recordId)
				.addValue("NAME", productDTO.getName()).addValue("QUANTITY", productDTO.getQuantity())
				.addValue("PRICE", productDTO.getPrice()).addValue("SKU", productDTO.getSku())
				.addValue("DEPARTMENT_ID", productDTO.getDepartmentId())
				.addValue("DESCRIPTION", productDTO.getDescription());

		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters);
		productDTO.setRecordId(recordId);
		return productDTO;
	}

	public List<ProductDTO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new ProductRowMapper());
	}

	public ProductDTO getById(String recordId) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY,
				new MapSqlParameterSource("RECORD_ID", recordId), new ProductRowMapper());
	}

	public ProductDTO update(ProductDTO productDTO, String recordId) {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("RECORD_ID", recordId)
				.addValue("NAME", productDTO.getName()).addValue("QUANTITY", productDTO.getQuantity())
				.addValue("PRICE", productDTO.getPrice()).addValue("SKU", productDTO.getSku())
				.addValue("DEPARTMENT_ID", productDTO.getDepartmentId())
				.addValue("DESCRIPTION", productDTO.getDescription());

		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		productDTO.setRecordId(recordId);
		if (recordsUpdated != 1) {
			throw new DatabaseException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + recordId + " not found");
		}
		return productDTO;
	}

	public int deleteById(String recordId) {
		
		  SqlParameterSource namedParameters = new MapSqlParameterSource("RECORD_ID", recordId); 
		  return namedParameterJdbcTemplate.update(DELETE_QUERY,namedParameters);
		  
		 
	}
}