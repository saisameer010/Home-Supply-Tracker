package com.SupplyTracker.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.SupplyTracker.Pojo.Product;

public class ProductMapper implements RowMapper<Product> {
	// this Function is Utilized by DbAccessDAOImpl to give output in required format
	public Product mapRow(ResultSet resultSet, int i) throws SQLException {

		Product person = new Product();
		person.setName(resultSet.getString("name"));
		person.setType(resultSet.getString("type"));
		person.setQuantity(resultSet.getInt("quantity"));
		person.setPrice(resultSet.getFloat("price"));
		person.setDesc(resultSet.getString("description"));
		return person;
	}
}