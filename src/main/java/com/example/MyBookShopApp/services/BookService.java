package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.getBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitleContaining(title);
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.getBooksByPriceBetween(min, max);
    }

    public List<Book> getBooksOrderByTag() {
        return bookRepository.findAllByOrderByTag();
    }

    public List<Book> getBooksByTag(String tag) {
        return bookRepository.findAllByTagTagName(tag);
    }

    public List<Book> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBooksOrderByGenre() {
        return bookRepository.findAllByOrderByGenreName();
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findAllByGenreName(genre);
    }

    public List<Book> getBooksOrderByAuthor() {
        return bookRepository.findAllByOrderByAuthor();
    }

    //Methods for get books page

    public Page<Book> getBestsellers() {
        return new PageImpl<>(bookRepository.getBestsellers());
    }

    public Page<Book> getPageOfRecommendedBooks(Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfNewBooks(Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        return new PageImpl<>(bookRepository.findBooksByPubDate(nextPage));
    }

    public Page<Book> getPageOfPopularBooks(Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        return new PageImpl<>(bookRepository.getBestsellersPage(nextPage));
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        return new PageImpl<>(bookRepository.getBookByTitleContaining(searchWord, nextPage));
    }

    public Page<Book> getBooksPageByAuthor(String authorName, Integer page, Integer limit) {
        PageRequest nextPage = PageRequest.of(page, limit);
        if (authorName.isEmpty()) {
            return new PageImpl<>(bookRepository.getBooksOrderByAuthor(nextPage));
        } else {
            return new PageImpl<>(bookRepository.getBooksByAuthorLastName(authorName, nextPage));
        }
    }

    public Page<Book> getBooksPageByGenre(String genre, Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        if (genre.isEmpty()) {
            return new PageImpl<>(bookRepository.getBooksOrderByGenre());
        } else {
            return new PageImpl<>(bookRepository.getBookPageByGenreName(genre, nextPage));
        }
    }
}
