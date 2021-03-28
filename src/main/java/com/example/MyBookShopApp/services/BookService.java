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

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getNewBooks() {
        return bookRepository.findAll();
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

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.getBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public Page<Book> getBestsellers() {
        return new PageImpl<>(bookRepository.getBestsellers());
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfNewBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new PageImpl<>(bookRepository.findBooksByPubDate(nextPage));
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new PageImpl<>(bookRepository.getBestsellersPage(nextPage));
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new PageImpl<>(bookRepository.getBookByTitleContaining(searchWord, nextPage));
    }

//    public Page<Book> getPageBooksByTag(String tag, Integer offset, Integer limit) {
//        Pageable nextPage = PageRequest.of(offset, limit);
//        return new PageImpl<>(bookRepository.getBooksByTag(tag, nextPage));
//    }
//
//    public Page<Book> getPageBooksByGenre(String genre, Integer offset, Integer limit) {
//        Pageable nextPage = PageRequest.of(offset, limit);
//        return new PageImpl<>(bookRepository.getBooksByGenre(genre, nextPage));
//    }

/*    public Page<Book> getPageBooksByAuthor(String author, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new PageImpl<>(bookRepository.getBooksByAuthor(author, nextPage));
    }*/
}
