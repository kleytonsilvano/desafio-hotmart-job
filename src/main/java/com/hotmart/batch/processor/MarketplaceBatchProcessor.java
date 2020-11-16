package com.hotmart.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.hotmart.models.db.Category;

public class MarketplaceBatchProcessor implements ItemProcessor<Category, String> {

	@Override
	public String process(Category data) throws Exception {
		return data.getName();
	}

}