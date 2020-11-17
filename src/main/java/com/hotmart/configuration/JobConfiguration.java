package com.hotmart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.hotmart.batch.reader.MarketplaceBatchReader;
import com.hotmart.batch.steps.MarketplaceBatchProcessor;
import com.hotmart.batch.writer.MarketplaceBatchWriter;
import com.hotmart.utils.DateUtils;

@Configuration
public class JobConfiguration {
	
	@Bean
	public MarketplaceBatchProcessor getMarketplaceBatchProcessor() {
		return new MarketplaceBatchProcessor();
	}

	@Bean
	public MarketplaceBatchWriter getMarketplaceBatchWriter() {
		return new MarketplaceBatchWriter();
	}

	@Bean
	public MarketplaceBatchReader MarketplaceBatchReader() {
		return new MarketplaceBatchReader();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	 
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(3000);
	    factory.setReadTimeout(3000);
	    return new RestTemplate(factory);
	    
	}

	@Bean
	public DateUtils getDateUtils() {
		return new DateUtils();
	}

}
