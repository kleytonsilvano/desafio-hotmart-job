package com.hotmart.models;

import com.hotmart.models.db.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryApiNewsContext {
	
	private Category category;
	private Integer amountNewsCategory;
	private Integer amountNewskeyword;
	
}
