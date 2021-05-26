package com.news.batch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.news.batch.processor.CustomItemProcessor;
import com.news.batch.processor.steps.CreateCategoryApiNewsVOStep;
import com.news.batch.processor.steps.CreateScoreProductStep;
import com.news.batch.processor.steps.SearchByCategoryApiNewsStep;
import com.news.batch.processor.steps.SearchByKeywordApiNewsStep;
import com.news.batch.processor.steps.StatisticsProductsStep;
import com.news.batch.reader.MarketplaceBatchReader;
import com.news.batch.writer.MarketplaceBatchWriter;
import com.news.models.db.Category;
import com.news.models.db.ScoreProduct;

@Configuration
public class MarketplaceBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	public final static String jobName = "MarketplaceJob1";

	@Autowired
	public JpaTransactionManager jpaTransactionManager;

	@Bean
	public Job processJob(Step step, JobExecutionListener listener) {
		return jobBuilderFactory.get(jobName)
				.listener(listener)
				.flow(step)
				.end().build();
	}

	@Bean
	public Step getFlowStep(MarketplaceBatchReader reader,
			CreateCategoryApiNewsVOStep createCategoryApiNewsVOStep,
			SearchByCategoryApiNewsStep searchByCategoryApiNewsStep,
			SearchByKeywordApiNewsStep searchByKeywordApiNewsStep,
			StatisticsProductsStep statisticsProductsStep,
			CreateScoreProductStep createScoreProductStep,
			MarketplaceBatchWriter writer) {

		return stepBuilderFactory.get("orderJob")
				.transactionManager(jpaTransactionManager)
				.<Category, List<ScoreProduct>> chunk(1)
				.reader(reader)
				.processor(new CustomItemProcessor()
						.add(createCategoryApiNewsVOStep)
						.add(searchByCategoryApiNewsStep)
						.add(searchByKeywordApiNewsStep)
						.add(statisticsProductsStep)
						.add(createScoreProductStep))
				.writer(writer)
				.build();

	}

}
