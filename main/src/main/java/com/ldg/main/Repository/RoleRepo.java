package com.ldg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldg.main.Models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
}
