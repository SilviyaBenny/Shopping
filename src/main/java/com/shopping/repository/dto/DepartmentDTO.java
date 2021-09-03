package com.shopping.repository.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

	private int id;

	private String departmentName;

	private String description;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

}
