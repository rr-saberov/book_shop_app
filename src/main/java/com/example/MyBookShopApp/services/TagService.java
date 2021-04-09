package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Tag;
import com.example.MyBookShopApp.repositories.BookRepository;
import com.example.MyBookShopApp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final BookRepository bookRepository;

    @Autowired
    public TagService(TagRepository tagRepository, BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.bookRepository = bookRepository;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public List<Book> getBooksOrderByTag() {
        return bookRepository.getBooksOrderByTag();
    }

    public Page<Book> getBooksWithTag(String tagName, Integer page, Integer limit) {
        PageRequest nextPage = PageRequest.of(page, limit);
        if (tagName.isEmpty()) {
            return new PageImpl<>(bookRepository.getBooksPageOrderByTag(nextPage));
        } else {
            return new PageImpl<>(bookRepository.getBooksByTagName(tagName, nextPage));
        }
    }
}
