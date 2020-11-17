package com.hotmart.models.vo;

import com.hotmart.models.db.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryApiNewsVO {
	
	private Category category;
	private Integer amountNews;
	
}
