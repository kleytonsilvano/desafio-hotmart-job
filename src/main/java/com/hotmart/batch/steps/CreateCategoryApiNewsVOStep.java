package com.hotmart.batch.steps;

import org.springframework.batch.item.ItemProcessor;

import com.hotmart.models.db.Category;
import com.hotmart.models.vo.CategoryApiNewsVO;

public class CreateCategoryApiNewsVOStep implements ItemProcessor<Category, CategoryApiNewsVO> {

	@Override
	public CategoryApiNewsVO process(Category category) throws Exception {
		
		CategoryApiNewsVO vo = new CategoryApiNewsVO();
		vo.setCategory(category);
		
		return vo;
	}

}
