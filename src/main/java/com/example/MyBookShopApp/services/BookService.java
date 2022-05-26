package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.getBooksByAuthorLastNameContaining(authorName).stream()
                .map(book -> book.add(Link.of("http://localhost:8085/api/books/by-author")))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.isEmpty()) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.getBookByTitleContaining(title)
                    .stream()
                    .map(book -> book.add(Link.of("http://localhost:8085/api/books/by-title")))
                    .collect(Collectors.toList());
            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Double min, Double max) {
        return bookRepository.getBooksByPriceBetween(min, max).stream()
                .map(book -> book.add(Link.of("http://localhost:8085/api/books/by-price-range")))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksOrderByTag() {
        return bookRepository.findAllByOrderByTag();
    }

    public List<Book> getBooksByTag(String tag) {
        return bookRepository.findAllByTagTagName(tag);
    }

    public List<Book> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount().stream()
                .map(book -> book.add(Link.of("http://localhost:8085/api/books/with-max-price")))
                .collect(Collectors.toList());
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
        return bookRepository.getBestsellers().stream()
                .map(book -> book.add(Link.of("http://localhost:8085/api/books/bestsellers")))
                .collect(Collectors.toList());
    }

    //Methods to get books page
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
