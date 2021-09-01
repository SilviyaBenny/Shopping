package com.shopping.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentBO {

	private int id;
	private String departmentName;
	private String description;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

}
