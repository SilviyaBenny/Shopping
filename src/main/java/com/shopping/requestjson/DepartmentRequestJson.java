package com.shopping.requestjson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestJson {

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
