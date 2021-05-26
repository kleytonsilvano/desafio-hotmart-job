package com.news.batch;

import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MarketplaceQuartzJobBean extends QuartzJobBean {

    private static final String JOB_NAME = "jobName";

    private JobLocator jobLocator;

    private JobLauncher jobLauncher;
    
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();

        try {

            Map< String, Object> jobDataMap = context.getMergedJobDataMap();

            String jobName = (String) jobDataMap.get(JOB_NAME);

            jobLauncher.run(jobLocator.getJob(jobName), jobParameters);

        } catch (Exception e) {
        	
            e.printStackTrace();

        }

    }

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

}
