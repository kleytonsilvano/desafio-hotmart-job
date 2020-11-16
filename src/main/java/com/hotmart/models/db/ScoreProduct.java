package com.hotmart.models.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_score_product")
public class ScoreProduct implements Serializable{

	private static final long serialVersionUID = 7154471371618630597L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Long idProduct;

    @Column(precision=10, scale=4)
	private Double score;
    
    

}