package com.hotmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.models.db.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}