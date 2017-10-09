package com.techshop.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInformation {
	private String productName;
	private String alias;
	private String descriptionHTML;
	private String image1;
	private String image2;
	private String image3;
	private Integer price;
	private List<SpecificationInformation> specificationInfos;
	private Long manufacturerId;
	private Long categoryId;
}
