package com.shopping.responsejson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseJson {

	@JsonProperty("id")
	private int id;

	@JsonProperty("departmentName")
	private String departmentName;

	@JsonProperty("description")
	private String description;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("createdDate")
	private Date createdDate;

	@JsonProperty("modifiedBy")
	private String modifiedBy;

	@JsonProperty("modifiedDate")
	private Date modifiedDate;

}
