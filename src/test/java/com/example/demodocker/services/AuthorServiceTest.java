package com.example.demodocker.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demodocker.entity.Author;
import com.example.demodocker.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorService.class})
@ExtendWith(SpringExtension.class)
public class AuthorServiceTest {
    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testAddAuthor() {
        Author author = new Author();
        author.setAuthorName("Pradeep");
        author.setAuthorImage("singh");
        author.setId(1);
        author.setAuthorDescription("SDE@Hashedin");
        when(this.authorRepository.save((Author) any())).thenReturn(author);

        Author author1 = new Author();
        author1.setAuthorName("Pradeep");
        author1.setAuthorImage("singh");
        author1.setId(1);
        author1.setAuthorDescription("SDE@Hashedin");
        assertSame(author, this.authorService.addAuthor(author1));
        verify(this.authorRepository).save((Author) any());
    }

    @Test
    public void testListAll() {
        ArrayList<Author> authorList = new ArrayList<Author>();
        when(this.authorRepository.findAll()).thenReturn(authorList);
        List<Author> actualListAllResult = this.authorService.listAll();
        assertSame(authorList, actualListAllResult);
        assertTrue(actualListAllResult.isEmpty());
        verify(this.authorRepository).findAll();
    }
}

