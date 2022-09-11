package com.ldg.main.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());
    }

}
