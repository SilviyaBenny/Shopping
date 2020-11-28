package com.shopping.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shopping.repository.dao.ProductDAO;

public class ProductRowMapper implements RowMapper<ProductDAO> {
	@Override
	public ProductDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDAO productdao = new ProductDAO();

		productdao.setId(rs.getInt("ID"));
		productdao.setName(rs.getString("NAME"));
		productdao.setQuantity(rs.getInt("QUANTITY"));
		productdao.setPrice(rs.getFloat("PRICE"));
		productdao.setSku(rs.getString("SKU"));
		productdao.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
		return productdao;
	}
}
