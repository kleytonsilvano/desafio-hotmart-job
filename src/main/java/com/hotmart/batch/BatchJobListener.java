package com.hotmart.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.dao.MarketplaceDAO;

public class BatchJobListener extends JobExecutionListenerSupport {

	@Autowired
	private MarketplaceDAO dao;
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("JOB COMPLETED SUCCESSFULLY");
		}
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		dao.deleteAllScoreProduct();
		System.out.println("JOB STARTING ...");
	}

}
