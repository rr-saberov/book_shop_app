package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class BookServiceTest {

    List<Book> expectedList;
    final BookService bookService;
    final BookRepository bookRepository;

    @Autowired
    BookServiceTest(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void setUp() {
        expectedList = new ArrayList<>();
        expectedList.addAll(bookRepository.findAll());
    }

    @AfterEach
    void tearDown() {
        expectedList = null;
    }

    @Test
    void getBooksByAuthorTest() {
        assertArrayEquals(expectedList.stream().filter(book -> book.getAuthor().getLastName().equals("Mayman")).toArray(),
                bookService.getBooksByAuthor("Mayman").toArray());
    }

    @Test
    void getBooksByTitleTest() throws BookstoreApiWrongParameterException {
        assertArrayEquals(expectedList.stream().filter(book -> book.getTitle().contains("wa")).toArray(),
                bookService.getBooksByTitle("wa").toArray());
    }

    @Test
    void getBooksWithPriceBetweenTest() {
        assertArrayEquals(expectedList.stream()
                .filter(book -> book.getPrice() >= 495.33 && book.getPrice() <= 1945.13).toArray(),
                bookService.getBooksWithPriceBetween(495.33, 1945.13).toArray());
    }

    @Test
    void getBooksOrderByTagTest() {
        assertArrayEquals(expectedList.stream().sorted(Comparator.comparing(book -> book.getTag().getTagName())).toArray(),
                bookService.getBooksOrderByTag().toArray());
    }

    @Test
    void getBooksByTagTest() {
        assertArrayEquals(expectedList.stream().filter(book -> book.getTag().getTagName().contains("Ba")).toArray(),
                bookService.getBooksByTag("Ba").toArray());
    }

    @Test
    void getBooksWithMaxPriceTest() {
        assertArrayEquals(expectedList.stream().sorted(Comparator.comparing(Book::getPrice)).toArray(),
                bookService.getBooksWithMaxPrice().toArray());
    }

    @Test
    void getBooksOrderByGenreTest() {
        assertArrayEquals(expectedList.stream().sorted(Comparator.comparing(book -> book.getGenre().getName())).toArray(),
                bookService.getBooksOrderByTag().toArray());
    }

    @Test
    void getBooksByGenreTest() {
        assertArrayEquals(expectedList.stream().filter(book -> book.getGenre().equals("horror")).toArray(),
                bookService.getBooksByGenre("horror").toArray());
    }

    @Test
    void getBooksOrderByAuthorTest() {
        assertArrayEquals(expectedList.stream().sorted(Comparator.comparing(book -> book.getAuthor().getLastName())).toArray(),
                bookService.getBooksOrderByAuthor().toArray());
    }

    @Test
    void getBooksOrderByTagNameTest() {
        assertArrayEquals(expectedList.stream().filter(book -> book.getTag().getTagName().equals("Tag")).toArray(),
                bookService.getBooksWithTag("Tag", 5, 5).toList().toArray());
    }

    @Test
    void getBestsellersTest() {
        assertArrayEquals(expectedList.stream().filter(book -> book.getIsBestseller() == 1).toArray(),
                bookService.getBestsellers().toArray());
    }
}