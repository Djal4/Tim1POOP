package com.ldg.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.Sightseeing;

@Repository
public interface SightseeingRepository extends JpaRepository<Sightseeing, Long> {
    @Query(value = "CALL findSightseeingByOwnerId(?1);", nativeQuery = true)
    public List<Sightseeing> findByOwnerId(long id);

    @Query(value = "CALL findSightseeingByUserId(?1)", nativeQuery = true)
    public List<Sightseeing> findByUserId(long id);

    @Query(value = "CALL findMarkedSightseeingByAdId(?1)", nativeQuery = true)
    public List<Sightseeing> findMarkedSightseeingByAdId(long id);
}
