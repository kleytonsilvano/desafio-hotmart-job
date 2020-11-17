package com.hotmart.models.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsProductVO {
	
	private Long idProduct;
	private Double averageRating;
	private Double salesPerDay;
	private String categories;

}
