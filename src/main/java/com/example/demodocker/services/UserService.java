package com.example.demodocker.services;


import com.example.demodocker.entity.User;
import com.example.demodocker.exceptions.CannotDeleteUserException;
import com.example.demodocker.exceptions.UserNotFoundException;
import com.example.demodocker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void uploadImage(String image, Long id){
        userRepository.uploadImage(image, id);
    }

    public User getUserById(Long id){

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: "+ id + " not found."));
    }

    public void Delete(Long id){
        userRepository.findById(id).orElseThrow(() -> new CannotDeleteUserException("User not present in cart and hence cannot be deleted."));
        userRepository.deleteById(id);
    }
}
