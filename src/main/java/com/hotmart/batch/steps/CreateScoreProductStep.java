package com.hotmart.batch.steps;

import org.springframework.batch.item.ItemProcessor;

import com.hotmart.models.CategoryApiNewsContext;
import com.hotmart.models.db.ScoreProduct;

public class CreateScoreProductStep implements ItemProcessor<CategoryApiNewsContext, ScoreProduct> {

	@Override
	public ScoreProduct process(CategoryApiNewsContext context) throws Exception {
		
		ScoreProduct scoreProduct = new ScoreProduct();
		scoreProduct.setIdProduct(context.getStatisticsProductVO().getIdProduct());
		scoreProduct.setScore(addTotalScore(context));
		return scoreProduct;
	}

	private Double addTotalScore(CategoryApiNewsContext context) {
		
		Double total = 0.0;
		//Quantity of product category news for the current day
		total = Double.sum(total, getDoubleValue(context.getAmountNewsCategory())); //Sum amount news of category
		total = Double.sum(total, getDoubleValue(context.getAmountNewsKeyword())); //Sum amount news of keyword
		
		if(context.getStatisticsProductVO() != null) {
			
			//Average product rating over the past 12 months
			total = Double.sum(total, getDoubleValue(context.getStatisticsProductVO().getAverageRating()));

			//Quantity of sales / days since the product exists
			total = Double.sum(total, getDoubleValue(context.getStatisticsProductVO().getSalesPerDay()));
			
		}
		
		return total;
	}
	
	private Double getDoubleValue(Double value) {
		
		return value != null ? value : 0.0;
		
	}
	
	private Double getDoubleValue(Integer value) {
		
		return value != null ? Double.valueOf(value) : 0.0;
		
	}

}
