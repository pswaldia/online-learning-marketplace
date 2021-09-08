package com.example.demodocker.controllers;

import com.example.demodocker.entity.Role;
import com.example.demodocker.services.FileUploadUtil;
import com.example.demodocker.services.UserService;
import com.example.demodocker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/all")
    public ResponseEntity<List<User>> listAll(){
        List<User> users =  userService.listAll();
        return new ResponseEntity<List<User>> (users, HttpStatus.OK);
    }

    @PostMapping("/user/upload/{id}")
    public ResponseEntity uploadImage(@PathVariable Long  id,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "user-photos";
        userService.uploadImage(fileName, id);
        String urlImage = userService.getUserById(id).getPhotosImagePath();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/user/new")
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }


    @PutMapping("/user/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.Delete(id);
    }

}
