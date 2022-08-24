package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.AdCategory;

@Repository
public interface AdCategoryRepository extends JpaRepository<AdCategory, Long> {

}
