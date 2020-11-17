package com.hotmart.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronTrigger;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.hotmart.batch.BatchJobCompletionListener;
import com.hotmart.batch.MarketplaceBatchConfig;
import com.hotmart.batch.MarketplaceQuartzJobBean;

@SuppressWarnings("deprecation")
@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration extends DefaultBatchConfigurer {
	
	@Autowired
	private JobLauncher jobLauncher;
	
    @Bean
    public JobRegistry jobRegistry() {
        return new MapJobRegistry();
    }
    
    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }
    
    @Bean
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

	
	@Bean
	 public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.afterPropertiesSet();
        return simpleJobLauncher;
    }
	
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry());
		return jobRegistryBeanPostProcessor;
	}
	
	@Bean(name = "detailContext")
	 public ApplicationContextFactory getApplicationContext() {
        return new GenericApplicationContextFactory(MarketplaceBatchConfig.class);
    }
	
	@Bean(name = "detailJob")
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(MarketplaceQuartzJobBean.class);
		jobDetailFactoryBean.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("jobLauncher", jobLauncher);
		map.put("jobName", MarketplaceBatchConfig.jobName);
		jobDetailFactoryBean.setJobDataAsMap(map);
		return jobDetailFactoryBean;
	}
	
	@Bean(name = "cronJob")
	public CronTriggerFactoryBean cronTriggerFactoryBean() {

		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * 1/1 * ? *");

		return cronTriggerFactoryBean;

	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobRegistry jobRegistry) throws NoSuchJobException {

		List<CronTrigger> listCronTrigger = Arrays.asList(cronTriggerFactoryBean().getObject());
		CronTrigger[] itemsArray = new CronTrigger[listCronTrigger.size()];

		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setTriggers( listCronTrigger.toArray(itemsArray) );
		schedulerFactoryBean.setAutoStartup(true);
		Map<String, Object> map = new HashMap<>();
		map.put("jobLauncher", jobLauncher);
		map.put("jobLocator", jobRegistry);

		schedulerFactoryBean.setSchedulerContextAsMap(map);

		return schedulerFactoryBean;

	}
	
	@Bean
	public JobExecutionListener listener() {
		return new BatchJobCompletionListener();
	}
}
