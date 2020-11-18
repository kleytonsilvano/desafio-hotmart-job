package com.hotmart.batch.processor.steps;

import static com.hotmart.constants.JobConstants.DATE_FORMAT;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hotmart.models.ApiNewsResponse;
import com.hotmart.models.CategoryApiNewsContext;
import com.hotmart.utils.DateUtils;

public abstract class AbstractApiNews implements ItemProcessor<CategoryApiNewsContext, CategoryApiNewsContext> {

	@Value("${BASE_URL_API_NEWS}")
	private String urlBase;

	@Value("${API_KEY_API_NEWS}")
	private String apiKey;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Autowired
	private RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(AbstractApiNews.class);
	
	public ApiNewsResponse searchNewsByCategory(String path, String category) {
		
		String url = getUrlString(path, category);
		
		try {
			
			ResponseEntity<ApiNewsResponse> result = restTemplate.getForEntity(url, ApiNewsResponse.class);
			
			if(result.getStatusCode().is2xxSuccessful()
					&& result.getBody().getStatus().equalsIgnoreCase("ok")) {
				
				return result.getBody();
				
			}
			
		}catch(Exception e) {
			
			logger.error("Error on consume endpoint: "+ url + ", ERROR: "+e.getMessage());
			
		}
		
		return null;
		
	}
	
	protected String getUrlString(String path, String category) {
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(urlBase)
				.append(path);
		
		Date currentDate = new Date();
		
		return String.format(stringBuilder.toString(), 
					category,
					apiKey,
					dateUtils.getDateFormatted(new Date(), DATE_FORMAT), 
					dateUtils.getDateFormatted(dateUtils.addDaysDate(currentDate, -1), DATE_FORMAT));
		
	}
	
	
}
