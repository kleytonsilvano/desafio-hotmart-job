package com.news.batch.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.news.models.db.Category;
import com.news.models.db.ScoreProduct;

public class CustomItemProcessor implements ItemProcessor<Category, List<ScoreProduct>> {
	
	private List<ItemProcessor<Object, Object>> items;
	
	public CustomItemProcessor() {
		
		items = new ArrayList<>();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScoreProduct> process(Category item) throws Exception {
		
		Object input = item;
		for (ItemProcessor<Object, Object> itemProcessor : items) {
			input = itemProcessor.process(input);
		}
		
		return (List<ScoreProduct>) input;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomItemProcessor add(ItemProcessor item) {
		this.items.add(item);
		return this;
	}


}
