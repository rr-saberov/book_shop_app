package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Author;
import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<Author>> getAuthorsMap() {
        return authorRepository.findAll().stream()
                .collect(Collectors.groupingBy((Author a) -> a.getLastName().substring(0, 1)));
    }

    public Page<Book> getBooksPageByAuthor(String authorName, Integer page, Integer limit) {
        PageRequest nextPage = PageRequest.of(page, limit);
        if (authorName.isEmpty()) {
            return new PageImpl<>(authorRepository.getBooksOrderByAuthor(nextPage));
        } else {
            return new PageImpl<>(authorRepository.getBooksByAuthorLastName(authorName, nextPage));
        }
    }
}
