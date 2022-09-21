package com.ldg.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.Image;
import com.ldg.main.Models.User;
import com.ldg.main.Repository.ImageRepository;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.payload.request.ChangePasswordRequest;
import com.ldg.main.payload.request.ChangeUserRequest;
import com.ldg.main.payload.request.RegisterRequest;
import com.ldg.main.payload.request.UploadImageRequest;
import com.ldg.main.Models.UserDetailsImpl;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ImageRepository imageRepository;
    private Optional<Image> image;

    public UserService()
    {
        user=new User();
    }

    public User saveUser(RegisterRequest request) {
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setRoleID(1);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        
        return userRepository.save(user);
    }

    public boolean changePassword(User usr,ChangePasswordRequest request) 
    {
        if (encoder.matches(request.getOldPassword(), usr.getPassword())) {
            if ((request.getNewPassword()).equals(request.getNewPasswordConfirmed())) {
                if(userRepository.setPassword(encoder.encode(request.getNewPassword()),usr.getID())!=0)
                    return true;
            }
        }

        return false;
    }

    public Image uploadImage(Authentication auth,UploadImageRequest request)
    {
        long id=userRepository.findById(((UserDetailsImpl) auth.getPrincipal()).getID()).get().getID();
        long image_id=userRepository.findById(((UserDetailsImpl) auth.getPrincipal()).getID()).get().getPictureID();
        image=imageRepository.findById(image_id);
        Image img;
        if(image.isPresent()){
            img=image.get();
            img.setImage(request.getImage());
            imageRepository.update(img.getImage(),img.getId());
        }else{
            img=new Image(id,request.getImage());
            img=imageRepository.save(img);
            userRepository.upload(img.getId(),id);   
        }
        return img; 
    }

    public User updateUser(Long ID,ChangeUserRequest request)
    {
        if(userRepository.updateUser(request.getFirstName(), request.getLastName(), ID)!=0)
            return userRepository.findById(ID).get();
        return null;
    }

}
