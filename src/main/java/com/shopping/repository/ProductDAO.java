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
import com.shopping.repository.dto.ProductDTO;
import com.shopping.rowmapper.ProductRowMapper;

@Repository
public class ProductDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String INSERT_QUERY = "INSERT INTO PRODUCT (NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) VALUES (:NAME,:QUANTITY,:PRICE,:SKU,:DEPARTMENT_ID,:DESCRIPTION,:CREATED_BY,NOW(),:MODIFIED_BY,NOW())";
	private final String SELECT_ALL_QUERY = "SELECT ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM PRODUCT ";
	private final String SELECT_BY_ID_QUERY = "SELECT ID,NAME, QUANTITY,PRICE,SKU,DEPARTMENT_ID,DESCRIPTION,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE FROM PRODUCT WHERE ID = :ID";
	private final String UPDATE_QUERY = "UPDATE PRODUCT SET NAME=:NAME, QUANTITY=:QUANTITY,PRICE=:PRICE,SKU=:SKU,DEPARTMENT_ID=:DEPARTMENT_ID,DESCRIPTION=:DESCRIPTION,MODIFIED_BY=:MODIFIED_BY,MODIFIED_DATE=:MODIFIED_DATE  WHERE ID = :ID";
	private final String DELETE_QUERY = "DELETE FROM PRODUCT WHERE ID = :ID";

	public ProductDTO create(ProductDTO productDTO) {

		Date date = new Date();
		productDTO.setCreatedDate(date);
		productDTO.setModifiedDate(date);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new MapSqlParameterSource()

				.addValue("NAME", productDTO.getName()).addValue("QUANTITY", productDTO.getQuantity())
				.addValue("PRICE", productDTO.getPrice()).addValue("SKU", productDTO.getSku())
				.addValue("DEPARTMENT_ID", productDTO.getDepartmentId())
				.addValue("DESCRIPTION", productDTO.getDescription()).addValue("CREATED_BY", productDTO.getCreatedBy())
				.addValue("CREATED_DATE", productDTO.getCreatedDate()).addValue("MODIFIED_BY", "testUser")
				.addValue("MODIFIED_DATE", productDTO.getModifiedDate());

		namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters, keyHolder, new String[] { "ID" });
		productDTO.setId(keyHolder.getKey().intValue());
		return productDTO;
	}

	public List<ProductDTO> getAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, new ProductRowMapper());
	}

	public ProductDTO getById(int id) {
		return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new MapSqlParameterSource("ID", id),
				new ProductRowMapper());
	}

	public ProductDTO update(ProductDTO productDTO, int id) {

		productDTO.setModifiedDate(new Date());

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id)
				.addValue("NAME", productDTO.getName()).addValue("QUANTITY", productDTO.getQuantity())
				.addValue("PRICE", productDTO.getPrice()).addValue("SKU", productDTO.getSku())
				.addValue("DEPARTMENT_ID", productDTO.getDepartmentId())
				.addValue("DESCRIPTION", productDTO.getDescription())
				.addValue("MODIFIED_BY", productDTO.getModifiedBy())
				.addValue("MODIFIED_DATE", productDTO.getModifiedDate());

		int recordsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		productDTO.setId(id);

		if (recordsUpdated != 1) {
			throw new ItemNotFoundException(ErrorCode.SHOPPING_DATABASE_100, ErrorType.DATABASE,
					"Item with id " + id + " not found");
		}
		return productDTO;
	}

	public int deleteById(int id) {

		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);

	}
}