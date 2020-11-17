package com.hotmart.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.hotmart.models.db.ScoreProduct;

public class MarketplaceBatchWriter implements ItemWriter<ScoreProduct> {


	@Override
	public void write(List<? extends ScoreProduct> items) throws Exception {
		
		System.out.println("Writing the data " + items.size());
		System.out.println(items.get(0).getScore());
		
	}

}