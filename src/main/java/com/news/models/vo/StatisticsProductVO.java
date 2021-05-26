package com.news.models.vo;

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
	private Double scoreTotalProduct; //Total product score if already registered in the score product table
	private Long idScoreProduct; //ID table tb_score_product

}
