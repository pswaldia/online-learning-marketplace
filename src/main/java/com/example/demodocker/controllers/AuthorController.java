package com.example.demodocker.controllers;

import com.example.demodocker.entity.Author;
import com.example.demodocker.services.AuthorService;
import com.example.demodocker.services.FileUploadUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
@RequestMapping("api/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author/new")
    @ApiOperation(
            value = "Create a new Author",
            notes = "User provides Author representation in JSON format which is then deserialized into Author object using Jackson and then gets persisted in the" +
                    "database",
            response = Author.class
    )
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        return new ResponseEntity<Author>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @GetMapping("/author/all")
    @ApiOperation(
            value = "Lists all Authors who have enrolled as an Instructor"
    )
    public ResponseEntity<List<Author>> listAll(){
        return new ResponseEntity<List<Author>>(authorService.listAll(),HttpStatus.OK);
    }

}
