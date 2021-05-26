package com.news.batch.processor.steps;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.news.dao.MarketplaceDAO;
import com.news.models.CategoryApiNewsContext;

public class StatisticsProductsStep implements ItemProcessor<CategoryApiNewsContext, CategoryApiNewsContext> {

	@Autowired
	private MarketplaceDAO dao;
	
	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext context) throws Exception {
		context.setStatisticsProducts(dao.findStatistics(context.getCategory().getId()));
		return context;
	}

}
