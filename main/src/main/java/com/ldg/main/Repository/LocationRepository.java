package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
