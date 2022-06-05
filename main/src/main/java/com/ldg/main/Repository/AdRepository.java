package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldg.main.Models.Ad;

public interface AdRepository extends JpaRepository<Ad,Integer>{
    
}
