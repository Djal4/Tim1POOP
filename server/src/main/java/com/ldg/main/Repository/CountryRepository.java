package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldg.main.Models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
