package com.hotmart.batch.steps;

import org.springframework.batch.item.ItemProcessor;

import com.hotmart.models.db.Category;
import com.hotmart.models.db.ScoreProduct;

public class MarketplaceBatchProcessor implements ItemProcessor<Category, ScoreProduct> {

	@Override
	public ScoreProduct process(Category data) throws Exception {
		System.out.println(data.getName());
		ScoreProduct scoreProduct = new ScoreProduct();
		scoreProduct.setScore(Math.random());
		return scoreProduct;
	}

}