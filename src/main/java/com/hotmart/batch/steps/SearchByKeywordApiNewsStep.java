package com.hotmart.batch.steps;

import com.hotmart.constants.JobConstants;
import com.hotmart.models.ApiNewsResponse;
import com.hotmart.models.CategoryApiNewsContext;
import com.hotmart.models.db.Category;

public class SearchByKeywordApiNewsStep extends SearchApiNews {

	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext vo) throws Exception {
		
		Category category = vo.getCategory();
		ApiNewsResponse response = searchNewsByCategory(JobConstants.API_NEWS_PATH_KEYWORD, category.getName());
		vo.setAmountNewskeyword(response != null ? response.getTotalResults() : 0);
		
		return vo;
		
	}

}
