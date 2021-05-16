package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Author;
import com.example.MyBookShopApp.repositories.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {

    private List<Author> expectedList;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @Autowired
    AuthorServiceTest(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        expectedList = new ArrayList<>();
        expectedList.addAll(authorRepository.findAll());
    }

    @AfterEach
    void tearDown() {
        expectedList = null;
    }

    @Test
    void getAuthorsTest() {
        assertArrayEquals(expectedList.toArray(), authorService.getAuthors().toArray());
    }

    @Test
    void getAuthorByNameTest() {
        assertEquals(expectedList.stream().filter(author -> author.getFirstName().equals("Fonzie")).findFirst(),
                authorService.getAuthorByName("Fonzie"));
    }
}