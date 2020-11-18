package com.hotmart.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.hotmart.batch.processor.steps.CreateCategoryApiNewsVOStep;
import com.hotmart.batch.processor.steps.CreateScoreProductStep;
import com.hotmart.batch.processor.steps.SearchByCategoryApiNewsStep;
import com.hotmart.batch.processor.steps.SearchByKeywordApiNewsStep;
import com.hotmart.batch.processor.steps.StatisticsProductsStep;
import com.hotmart.batch.reader.MarketplaceBatchReader;
import com.hotmart.batch.writer.MarketplaceBatchWriter;
import com.hotmart.dao.MarketplaceDAO;
import com.hotmart.utils.DateUtils;

@Configuration
public class JobConfiguration {

	@Bean
	public CreateCategoryApiNewsVOStep getCreateCategoryApiNewsVOStep() {
		return new CreateCategoryApiNewsVOStep();
	}

	@Bean
	public SearchByCategoryApiNewsStep getSearchByCategoryApiNewsStep() {
		return new SearchByCategoryApiNewsStep();
	}

	@Bean
	public SearchByKeywordApiNewsStep getSearchByKeywordApiNewsStep() {
		return new SearchByKeywordApiNewsStep();
	}

	@Bean
	public CreateScoreProductStep getCreateScoreProductStep() {
		return new CreateScoreProductStep();
	}

	@Bean
	public StatisticsProductsStep getStatisticsProductsStep() {
		return new StatisticsProductsStep();
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

	@Bean
	public MarketplaceDAO getMarketplaceDAO() {
		return new MarketplaceDAO();
	}

	@Bean
	@Primary
	public JpaTransactionManager jpaTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Autowired
	private DataSource dataSource;


}
