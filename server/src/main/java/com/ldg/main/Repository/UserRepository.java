package com.ldg.main.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Transactional
    @Modifying
    @Query(value="UPDATE users u SET u.role_id=?1 WHERE u.id=?2", nativeQuery=true)
    int updateRole(long roleID,long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE users u SET u.password=?1 WHERE u.id=?2",nativeQuery=true)
    int setPassword(String password,long ID);

    @Transactional
    @Modifying
    @Query(value="UPDATE users u SET u.firstname=?1,u.lastname=?2 WHERE u.id=?3",nativeQuery=true)
    int updateUser(String firstname,String lastname,Long ID);

    Optional<User> findByUsername(String username);
}
