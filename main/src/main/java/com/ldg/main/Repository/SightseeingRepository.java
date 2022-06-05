package com.ldg.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ldg.main.Models.Sightseeing;

public interface SightseeingRepository extends JpaRepository<Sightseeing,Long> {
    @Query(name ="CALL findByOwnerId(?1)",nativeQuery = true)
    List<Sightseeing> findByOwnerId(int id);
}
