package com.hotmart.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotmart.batch.processor.MarketplaceBatchProcessor;
import com.hotmart.batch.reader.MarketplaceBatchReader;
import com.hotmart.batch.writer.MarketplaceBatchWriter;
import com.hotmart.models.db.Category;

@Configuration
public class MarketplaceBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job processJob() {
 		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.flow(getFlowStep())
				.end().build();
	}

	@Bean
	public Step getFlowStep() {
		
		return stepBuilderFactory.get("orderStep1").<Category, String> chunk(1)
				.reader(new MarketplaceBatchReader())
				.processor(new MarketplaceBatchProcessor())
				//.processor(new MarketplaceBatchProcessor2())
				//.processor(new MarketplaceBatchProcessor())
				.writer(new MarketplaceBatchWriter())
			.build();
		
	}
	
}
