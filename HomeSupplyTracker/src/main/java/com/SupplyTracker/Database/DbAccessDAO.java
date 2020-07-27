package com.SupplyTracker.Database;

import java.util.List;

import com.SupplyTracker.Pojo.Product;
//This is the interface for DbAccessDAOImpl 
public interface DbAccessDAO {
	public void Insert(Product p);

	public int Update(Product p);

	public float SelectPrice(String str);

	public String SelectDescription(String str);

	public Product SelectProduct(String str);

	public List<Product> Select();
}
