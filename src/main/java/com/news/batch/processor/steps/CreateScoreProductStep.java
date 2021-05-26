package com.news.batch.processor.steps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.news.models.CategoryApiNewsContext;
import com.news.models.db.ScoreProduct;
import com.news.models.vo.StatisticsProductVO;

public class CreateScoreProductStep implements ItemProcessor<CategoryApiNewsContext, List<ScoreProduct>> {

	@Override
	public List<ScoreProduct> process(CategoryApiNewsContext context) throws Exception {
		
		List<ScoreProduct> listScore = new ArrayList<>();
		
		if(context.getStatisticsProducts() != null) {
			
			for (StatisticsProductVO vo : context.getStatisticsProducts()) {
				
				ScoreProduct scoreProduct = new ScoreProduct();
				scoreProduct.setId(getIdScoreProduct(vo.getIdScoreProduct()));
				scoreProduct.setIdProduct(vo.getIdProduct());
				scoreProduct.setScore( calculateTotalScore(context, vo) );
				
				//add in list
				listScore.add(scoreProduct);
				
			}
			
		}

		return listScore;
		
	}

	private Long getIdScoreProduct(Long idScoreProduct) {
		
		return (idScoreProduct != null && idScoreProduct > 0) ? idScoreProduct : null ;
	}

	private Double calculateTotalScore(CategoryApiNewsContext context, StatisticsProductVO vo) {
		
		Double total = 0.0;
		
		//Sum amount news about the CATEGORY
		total = Double.sum(total, getDoubleValue(context.getAmountNewsCategory())); //Sum amount news of category
		total = Double.sum(total, getDoubleValue(context.getAmountNewsKeyword())); //Sum amount news of keyword
		
		//If the product has already been registered, do not make this sum
		if(vo.getIdScoreProduct() == null || vo.getIdScoreProduct() == 0) {
			
			//Average product rating over the past 12 months
			total = Double.sum(total, getDoubleValue(vo.getAverageRating()));

			//Quantity of sales / days since the product exists
			total = Double.sum(total, getDoubleValue(vo.getSalesPerDay()));
			
		} else {
			
			//If score already been registered, just sum your score with amount the news of your category
			total = Double.sum(total, vo.getScoreTotalProduct());
			
		}
		
		return total;
		
	}
	
	private Double getDoubleValue(Double value) {
		
		return value != null ? value : 0.0;
		
	}
	
	private Double getDoubleValue(Integer value) {
		
		return value != null ? Double.valueOf(value) : 0.0;
		
	}

}
