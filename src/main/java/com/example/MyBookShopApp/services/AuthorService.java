package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Author;
import com.example.MyBookShopApp.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Map<String, List<Author>> getAuthorsMap() {
        return authorRepository.findAll().stream()
                .collect(Collectors.groupingBy((Author a) -> a.getLastName().substring(0, 1)));
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorByName(String name) {
        return authorRepository.getAuthorByFirstName(name);
    }
}
