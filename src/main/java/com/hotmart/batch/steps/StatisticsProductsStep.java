package com.hotmart.batch.steps;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.dao.MarketplaceDAO;
import com.hotmart.models.CategoryApiNewsContext;

public class StatisticsProductsStep implements ItemProcessor<CategoryApiNewsContext, CategoryApiNewsContext> {

	@Autowired
	private MarketplaceDAO dao;
	
	@Override
	public CategoryApiNewsContext process(CategoryApiNewsContext context) throws Exception {
		context.setStatisticsProductVO(dao.findStatistics());
		return context;
	}

}
