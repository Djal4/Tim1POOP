package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
