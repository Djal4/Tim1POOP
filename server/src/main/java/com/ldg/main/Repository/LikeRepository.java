package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigInteger;
import java.util.*;
import com.ldg.main.Models.*;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
    @Query(value = "CALL numberOfLikes(?1)", nativeQuery = true)
    public Map<String, BigInteger> numberOfLikes(long adId);

    @Query(value = "CALL userLikedAd(?1,?2)", nativeQuery = true)
    public Map<String, BigInteger> userLikedAd(long adId, long userId);

    @Query(value = "CALL toogleLike(?1,?2)", nativeQuery = true)
    public Map<String, Integer> toogleLike(long adId, long userId);
}