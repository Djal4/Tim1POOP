package com.ldg.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Query(value = "CALL findMyAds(?1)", nativeQuery = true)
    public List<Ad> findMyAds(long id);

    @Query(value = "CALL findOtherAds(?1)", nativeQuery = true)
    public List<Ad> findOtherAds(long id);
}
