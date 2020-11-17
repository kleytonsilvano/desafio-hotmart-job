package com.hotmart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hotmart.models.vo.StatisticsProductVO;

public class MarketplaceDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_STATISTICS_PRODUCT = 
			new StringBuilder()
			.append("-- PRODUCTS STATISTICS - CALCULATIONS TO SCORE EACH REGISTERED PRODUCT ")
			.append(" SELECT p.id AS id_product, ")
			.append("		( SELECT  ")
			.append("        	(sum(s.rate)/count(*)) ")
			.append("          FROM tb_sale s ")
			.append("          WHERE s.product_id = id_product ")
			.append("          AND s.purchase_date > DATE_SUB(NOW(),INTERVAL 1 YEAR)  ")
			.append("        ) AS average_rating, ") //AVERAGE PRODUCT RATING OVER THE PAST 12 MONTHS -- IF 'NULL' ITS BECAUSE NO SALE HAS BEEN MADE YET
			.append("        ( SELECT  ")
			.append("        	( count(*) / (DATEDIFF(NOW(), date(p.creation_date)) .append(1)) ")
			.append("          FROM tb_sale s ")
			.append("          WHERE s.product_id = id_product ")
			.append("        ) AS sales_day, ") //QUANTITY OF SALES / DAYS SINCE THE PRODUCT EXISTS
			.append("        (	SELECT  GROUP_CONCAT( c.name SEPARATOR ',')  ")
			.append("         	FROM tb_category c ")
			.append("         	INNER JOIN tb_product_categories pc on c.id = pc.category_id ")
			.append("         	WHERE pc.product_id = id_product ")
			.append("        ) AS categories ") //CATEGORIES OF PRODUCT
			.append("FROM tb_product p ")
			.toString();
	
	public StatisticsProductVO findStatistics() {
		
        return jdbcTemplate.queryForObject(
        			SQL_GET_STATISTICS_PRODUCT, (rs, rowNum) ->
        						new StatisticsProductVO(
                        rs.getLong("id_product"),
                        rs.getDouble("average_rating"),
                        rs.getDouble("sales_day"),
                        rs.getString("categories")
                ));

    }
}
