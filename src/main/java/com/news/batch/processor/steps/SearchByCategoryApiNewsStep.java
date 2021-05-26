package com.news.batch.processor.steps;

import com.news.constants.JobConstants;
import com.news.models.ApiNewsResponse;
import com.news.models.CategoryApiNewsContext;
import com.news.models.db.Category;

public class SearchByCategoryApiNewsStep extends AbstractApiNews {

	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext context) throws Exception {
		
		Category category = context.getCategory();
		ApiNewsResponse response = searchNewsByCategory(JobConstants.API_NEWS_PATH_CATEGORY, category.getName());
		context.setAmountNewsCategory(response != null ? response.getTotalResults() : 0);
		
		return context;
		
	}

}
