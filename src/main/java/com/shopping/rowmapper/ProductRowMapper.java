package com.shopping.rowmapper;

import java.util.UUID;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shopping.repository.dto.ProductDTO;

public class ProductRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setRecordId(rs.getString("RECORD_ID"));
		productDTO.setName(rs.getString("NAME"));
		productDTO.setQuantity(rs.getInt("QUANTITY"));
		productDTO.setPrice(rs.getFloat("PRICE"));
		productDTO.setSku(rs.getString("SKU"));
		productDTO.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
		productDTO.setDescription(rs.getString("DESCRIPTION"));
		return productDTO;
	}
}
