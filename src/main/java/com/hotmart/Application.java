package com.hotmart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		new Application().runJob();
	}
	
	//TODO DELETE 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
    public void runJob() throws Exception {
        jobLauncher.run(processJob, null);
    }
	 
}
