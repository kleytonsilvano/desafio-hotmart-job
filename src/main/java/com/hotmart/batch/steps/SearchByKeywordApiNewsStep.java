package com.hotmart.batch.steps;

import com.hotmart.constants.JobConstants;
import com.hotmart.models.ApiNewsResponse;
import com.hotmart.models.CategoryApiNewsContext;
import com.hotmart.models.db.Category;

public class SearchByKeywordApiNewsStep extends SearchApiNews {

	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext context) throws Exception {
		
		Category category = context.getCategory();
		ApiNewsResponse response = searchNewsByCategory(JobConstants.API_NEWS_PATH_KEYWORD, category.getName());
		context.setAmountNewsKeyword(response != null ? response.getTotalResults() : 0);
		
		return context;
		
	}

}
