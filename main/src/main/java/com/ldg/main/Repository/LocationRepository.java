package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldg.main.Models.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{
    
}
