package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
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
        return bookRepository.getBooksByAuthorLastNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.equals("") || title.length() <= 1) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.getBookByTitleContaining(title);
            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Double min, Double max) {
        return bookRepository.getBooksByPriceBetween(min, max);
    }

    public List<Book> getBooksOrderByTag() {
        return bookRepository.findAllByOrderByTag();
    }

    public List<Book> getBooksByTag(String tag) {
        return bookRepository.findAllByTagTagName(tag);
    }

    public List<Book> getBooksWithMaxPrice() {
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

    public Page<Book> getBooksWithTag(String tagName, Integer page, Integer limit) {
        PageRequest nextPage = PageRequest.of(page, limit);
        if (tagName.isEmpty()) {
            return new PageImpl<>(bookRepository.getBooksPageOrderByTag(nextPage));
        } else {
            return new PageImpl<>(bookRepository.getBooksByTagName(tagName));
        }
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    //Methods for get books page

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
        Page<Book> books = bookRepository.getBookPageByTitleContaining(searchWord, nextPage);
        return books;
    }
}
