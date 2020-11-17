package com.hotmart.batch.steps;

import org.springframework.batch.item.ItemProcessor;

import com.hotmart.models.CategoryApiNewsContext;
import com.hotmart.models.db.Category;

public class CreateCategoryApiNewsVOStep implements ItemProcessor<Category, CategoryApiNewsContext> {

	@Override
	public CategoryApiNewsContext process(Category category) throws Exception {
		
		CategoryApiNewsContext vo = new CategoryApiNewsContext();
		vo.setCategory(category);
		
		return vo;
	}

}
