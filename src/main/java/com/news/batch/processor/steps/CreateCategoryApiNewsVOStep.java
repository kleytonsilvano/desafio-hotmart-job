package com.news.batch.processor.steps;

import org.springframework.batch.item.ItemProcessor;

import com.news.models.CategoryApiNewsContext;
import com.news.models.db.Category;

public class CreateCategoryApiNewsVOStep implements ItemProcessor<Category, CategoryApiNewsContext> {

	@Override
	public CategoryApiNewsContext process(Category category) throws Exception {
		
		CategoryApiNewsContext vo = new CategoryApiNewsContext();
		vo.setCategory(category);
		
		return vo;
	}

}
