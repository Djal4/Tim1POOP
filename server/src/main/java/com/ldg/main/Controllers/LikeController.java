package com.ldg.main.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.LikeRepository;
import com.ldg.main.payload.request.LikeRequest;

import java.util.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    LikeRepository likeRepository;

    @PostMapping
    public ResponseEntity<?> likeOrUnlike(@Valid @RequestBody LikeRequest likeRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Map<String, Integer> likeResponse = likeRepository.toogleLike(likeRequest.adId, user.getID());
        Map<String, String> map = new HashMap<>();
        if (likeResponse.get("status").intValue() == 1)
            map.put("message", "Liked");
        else
            map.put("message", "Unliked");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
