package com.news.batch.reader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.news.models.db.Category;
import com.news.repository.CategoryRepository;

public class MarketplaceBatchReader implements ItemReader<Category> {

	@Autowired
	private CategoryRepository repository;

	private Iterator<Category> categoryIterator;

	@BeforeStep
	public void before(StepExecution stepExecution) {
		List<Category> listCategory = repository.findAll();
		if(listCategory != null) {
			categoryIterator = listCategory.iterator();
		}
	}

	@Override
	public Category read() throws Exception {

		if(categoryIterator != null  && categoryIterator.hasNext()) {
			return categoryIterator.next();
		} else {
			return null;
		}

	}

}