package com.hotmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.models.db.ScoreProduct;

@Repository
public interface ScoreProductRepository extends JpaRepository<ScoreProduct, Long> {

}