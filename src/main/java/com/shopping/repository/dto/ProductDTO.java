package com.shopping.repository.dto;

public class ProductDTO {

	private int id;
	private String name;
	private int quantity;
	private float price;
	private String sku;
	private int departmentId;
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", sku="
				+ sku + " ,departmentId=" + departmentId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
}
