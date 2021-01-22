package com.shopping.requestjson;

public class DepartmentRequestJson {

	private String recordId;
	private String departmentName;
	private String description;

	@Override
	public String toString() {
		return "DepartmentRequestJson [recordId=" + recordId + ", departmentName=" + departmentName + ", description=" + description
				+ "]";
	}
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
}
