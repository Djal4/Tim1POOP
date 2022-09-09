package com.ldg.main.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Repository.AdCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/ad_category")
public class AdCategoryController {
    @Autowired
    AdCategoryRepository adCategoryRepository;

    @GetMapping
    public ResponseEntity<?> getAdCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(adCategoryRepository.findAll());
    }

}
