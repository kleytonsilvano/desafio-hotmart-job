package com.hotmart.batch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.exceptions.ValidationException;
import com.hotmart.models.db.Category;
import com.hotmart.repository.CategoryRepository;

public class MarketplaceBatchReader implements ItemReader<Category> {
	
	@Autowired
	private CategoryRepository repository;
	
	@Override
	public Category read() throws Exception {
		
		List<Category> categories = repository.findAll();
		
		if(categories != null) {
			
			for (Category category : categories)
				return category;

		} else {
			
			throw new ValidationException("Error on find categories");
			
		}
		
		return null;
		
	}

}