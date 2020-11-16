package com.hotmart.batch.processor;

import org.springframework.batch.item.ItemProcessor;

public class MarketplaceBatchProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}