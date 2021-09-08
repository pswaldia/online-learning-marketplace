package com.example.demodocker.services;

import com.example.demodocker.entity.Author;
import com.example.demodocker.entity.User;
import com.example.demodocker.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> listAll() {
        return authorRepository.findAll();
    }
}
