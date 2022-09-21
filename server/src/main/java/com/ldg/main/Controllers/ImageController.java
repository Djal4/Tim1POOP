package com.ldg.main.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ldg.main.Models.Image;
import com.ldg.main.Models.UserDetailsImpl;
import com.ldg.main.Repository.ImageRepository;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.UserService;
import com.ldg.main.payload.request.UploadImageRequest;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    
    @Autowired
    private UserService userService;
    private Authentication auth;

    @Autowired
    private ImageRepository imageRepository;

    private Optional<Image> image;
    
    @PostMapping("/upload")
    public Image uploadImage(@RequestBody UploadImageRequest request)
    {
        auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.uploadImage(auth,request);
    }
    @GetMapping("/show/{id}")
    public String showImage(@PathVariable(value="id")Long ID)
    {
        image=imageRepository.findById(ID);
        if(image.isPresent())
            return image.get().getImage();
        return "";
    }
}
