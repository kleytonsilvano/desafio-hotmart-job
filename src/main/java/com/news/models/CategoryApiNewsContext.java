package com.news.models;

import java.util.List;

import com.news.models.db.Category;
import com.news.models.vo.StatisticsProductVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryApiNewsContext {
	
	private Category category;
	private Integer amountNewsCategory;
	private Integer amountNewsKeyword;
	private List<StatisticsProductVO> statisticsProducts;
	
}
