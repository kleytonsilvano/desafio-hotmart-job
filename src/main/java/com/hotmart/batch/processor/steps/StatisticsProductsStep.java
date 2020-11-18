package com.hotmart.batch.processor.steps;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.dao.MarketplaceDAO;
import com.hotmart.models.CategoryApiNewsContext;

public class StatisticsProductsStep implements ItemProcessor<CategoryApiNewsContext, CategoryApiNewsContext> {

	@Autowired
	private MarketplaceDAO dao;
	
	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext context) throws Exception {
		context.setStatisticsProducts(dao.findStatistics(context.getCategory().getId()));
		return context;
	}

}
