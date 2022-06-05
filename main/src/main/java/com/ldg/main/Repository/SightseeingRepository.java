package com.ldg.main.Repository;

//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.ldg.main.Models.Sightseeing;

public interface SightseeingRepository extends JpaRepository<Sightseeing,Integer> {
   // @Query(name ="SELECT * FROM sightseeing s WHERE EXISTS( SELECT * FROM advertisment a WHERE s.advertisment_id=a.id AND s.owner_id=?1)",nativeQuery = true)
    //List<Sightseeing> findByOwnerId(int id);
}
