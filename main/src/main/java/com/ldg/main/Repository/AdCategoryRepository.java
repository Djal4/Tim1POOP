package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldg.main.Models.AdCategory;

public interface AdCategoryRepository extends JpaRepository<AdCategory,Integer>{
    
}
