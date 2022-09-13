package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.City;

import java.util.*;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    public List<City> findByCountryId(long id);
}
