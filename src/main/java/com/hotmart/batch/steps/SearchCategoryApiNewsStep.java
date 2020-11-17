package com.hotmart.batch.steps;

import com.hotmart.constants.JobConstants;
import com.hotmart.models.db.Category;
import com.hotmart.models.vo.ApiNewsResponse;
import com.hotmart.models.vo.CategoryApiNewsVO;

public class SearchCategoryApiNewsStep extends SearchApiNews {

	@Override
	public CategoryApiNewsVO process(CategoryApiNewsVO vo) throws Exception {
		
		Category category = vo.getCategory();
		ApiNewsResponse response = searchNewsByCategory(JobConstants.API_NEWS_PATH_CATEGORY, category.getName());
		vo.setAmountNewsCategory(response != null ? response.getTotalResults() : 0);
		
		return vo;
		
	}

}
