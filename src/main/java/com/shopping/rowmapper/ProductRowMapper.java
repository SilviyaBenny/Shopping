package com.shopping.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shopping.repository.dto.ProductDTO;

public class ProductRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(rs.getInt("ID"));
		productDTO.setName(rs.getString("NAME"));
		productDTO.setQuantity(rs.getInt("QUANTITY"));
		productDTO.setPrice(rs.getFloat("PRICE"));
		productDTO.setSku(rs.getString("SKU"));
		productDTO.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
		productDTO.setDescription(rs.getString("DESCRIPTION"));
		productDTO.setCreatedBy(rs.getString("CREATED_BY"));
		productDTO.setCreatedDate(rs.getDate("CREATED_DATE"));
		productDTO.setModifiedBy(rs.getString("MODIFIED_BY"));
		productDTO.setModifiedDate(rs.getDate("MODIFIED_DATE"));
		return productDTO;
	}
}
