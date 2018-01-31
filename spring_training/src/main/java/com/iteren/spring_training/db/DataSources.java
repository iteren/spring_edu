package com.iteren.spring_training.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class DataSources {

	@Bean(name="ds")
	public DataSource getSecurityDs() {
		MysqlDataSource  ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://localhost:3306/spring");
		ds.setUser("root");
		ds.setPassword("root");
		return ds;
	}
}
