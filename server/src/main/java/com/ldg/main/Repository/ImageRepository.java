package com.ldg.main.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ldg.main.Models.Image;
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Transactional
    @Modifying
    @Query(value="UPDATE images i SET i.image=?1 WHERE i.id=?2",nativeQuery=true)
    public int update(String image,Long id);
}
