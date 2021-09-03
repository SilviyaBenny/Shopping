package com.shopping.responsejson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseJson {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("price")
	private float price;

	@JsonProperty("sku")
	private String sku;

	@JsonProperty("departmentId")
	private int departmentId;

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
