package com.SupplyTracker.Pojo;

import org.springframework.stereotype.Component;

@Component
public class Product {
	public Product() {
		price = 0;
		desc = "None";
	}

	public Product(String name, String type, int quantity, float price, String desc) {
		super();
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.desc = desc;
	}

	private String name, desc, type;
	private int quantity, id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	float price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
