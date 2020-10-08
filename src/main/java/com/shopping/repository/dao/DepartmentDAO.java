package com.shopping.repository.dao;

public class DepartmentDAO {

	private int id;
	private String departmentName;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DepartmentDAO [id=" + id + ", departmentName=" + departmentName + ", description=" + description + "]";
	}
}
