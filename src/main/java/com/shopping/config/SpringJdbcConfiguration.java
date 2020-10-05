package com.shopping.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringJdbcConfiguration {
	@Value("${product.db.Url}")
	private String Url;
	@Value("${product.db.username}")
	private String username;
	@Value("${product.db.password}")
	private String password;

	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(Url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}
