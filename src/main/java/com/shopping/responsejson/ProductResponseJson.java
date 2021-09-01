package com.shopping.responsejson;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseJson {

	private int id;
	private String name;
	private int quantity;
	private float price;
	private String sku;
	private int departmentId;
	private String description;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

}
