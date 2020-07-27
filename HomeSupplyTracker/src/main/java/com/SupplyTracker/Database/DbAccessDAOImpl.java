package com.SupplyTracker.Database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SupplyTracker.Pojo.Product;

@Repository
public class DbAccessDAOImpl implements DbAccessDAO {
	// JdbcTemplate is used to Connect to the data base
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// setters and getters are required because we are using setter injection
	// through applicationContext.xml
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public DbAccessDAOImpl() {
	}
	// this is Used to insert records

	@Override
	public void Insert(Product p) {
		try {
			String str = "insert into products values (\'" + p.getName() + "\',\'" + p.getType() + "\',"
					+ p.getQuantity() + "," + p.getPrice() + ",\'" + p.getDesc() + "\');";

			jdbcTemplate.update(str);
		} catch (Exception e) {
			System.out.println("Insert error" + e);
		}
	}

	// this is Used to Update records
	@Override
	public int Update(Product p) {
		try {
			String str = "update products " + "set quantity = " + p.getQuantity() + " , price= " + p.getPrice()
					+ " , description= \'" + p.getDesc() + "\' where name Like \'" + p.getName() + "\' ;";
			return jdbcTemplate.update(str);
		} catch (Exception e) {
			System.out.println("Update error" + e);
			return 0;
		}

	}

	// this is Used to Get the price of a product from record
	// as the return type of JdbcTemplate's function queryForObject() is of type
	// Product We can access the Price of the product by using the products getter
	@Override
	public float SelectPrice(String str) {
		Product p;
		try {
			p = jdbcTemplate.queryForObject("select * from products where name =\'" + str + "\' ;",
					new ProductMapper());
			return p.getPrice();// .getFloat("price");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" price select " + e);
			e.printStackTrace();
			return 0;
		}
	}

	// this is Used to Get description of product
	// as the return type of JdbcTemplate's function queryForObject() is of type
	// Product We can access the Description of the product by using the products
	// getter
	@Override
	public String SelectDescription(String str) {
		Product p;
		try {
			p = jdbcTemplate.queryForObject("select * from products where name like \'" + str + "\' ;",
					new ProductMapper());
			return p.getDesc();// rs.getString(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" price select" + e);
			e.printStackTrace();
			return "Not given";
		}
	}

	// this is Used to get product from records
	@Override
	public Product SelectProduct(String str) {
		Product p;
		try {
			p = jdbcTemplate.queryForObject("select * from products where name like \'" + str + "\' ;",
					new ProductMapper());
			return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" prod select" + e);
			e.printStackTrace();
			return null;
		}
	}

	// this is Used to get all products records
	@Override
	public List<Product> Select() {

		try {
			return jdbcTemplate.query("select * from products ;", new ProductMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" select " + e);
			e.printStackTrace();
			return null;
		}
	}

}
