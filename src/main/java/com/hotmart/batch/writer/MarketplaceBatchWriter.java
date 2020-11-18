package com.hotmart.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.models.db.ScoreProduct;
import com.hotmart.repository.ScoreProductRepository;

public class MarketplaceBatchWriter implements ItemWriter<List<ScoreProduct>> {

	@Autowired
	private ScoreProductRepository repository;

	@Override
	public void write(List<? extends List<ScoreProduct>> items) throws Exception {

		for (List<ScoreProduct> list : items) {

			//All products of category contaning your score
			if(list.size() > 0) {
				
				List<ScoreProduct> findAll = repository.findAll();

				System.out.println("FindALL: " + findAll.size());
				System.out.println(list.size());
				repository.saveAll(list);
				
			}
			

		}

	}

}