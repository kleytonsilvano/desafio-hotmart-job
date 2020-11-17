package com.hotmart.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotmart.batch.reader.MarketplaceBatchReader;
import com.hotmart.batch.steps.MarketplaceBatchProcessor;
import com.hotmart.batch.writer.MarketplaceBatchWriter;
import com.hotmart.models.db.Category;
import com.hotmart.models.db.ScoreProduct;

@Configuration
public class MarketplaceBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	public final static String jobName = "MarketplaceJob1";

	@Bean
	public Job processJob(Step step, JobExecutionListener listener) {
 		return jobBuilderFactory.get(jobName)
			 		.listener(listener)
					.flow(step)
					.end().build();
	}

	@Bean
	public Step getFlowStep(MarketplaceBatchReader reader,
							MarketplaceBatchProcessor processor,
							MarketplaceBatchWriter writer) {
		
		return stepBuilderFactory.get("orderJob").<Category, ScoreProduct> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
			.build();
		
	}
	
	

}
