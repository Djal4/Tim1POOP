package com.ldg.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value="UPDATE users SET users.role_id=?1 WHERE users.id=?2", nativeQuery=true)
    boolean updateRole(int roleID,long id);

    Optional<User> findByUsername(String username);
}
