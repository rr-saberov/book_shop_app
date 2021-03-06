package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.BooksPageDto;
import com.example.MyBookShopApp.entities.SearchWordDto;
import com.example.MyBookShopApp.errs.EmptySearchException;
import com.example.MyBookShopApp.services.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks() {
        return bookService.getPageOfNewBooks(0, 6).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/books/recommended")
    public BooksPageDto getBooksPage(@RequestParam Integer offset, @RequestParam Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @ResponseBody
    @GetMapping("/books/recent")
    public BooksPageDto getNewBooksPage(@RequestParam Integer offset, @RequestParam Integer limit) {
        return new BooksPageDto(bookService.getPageOfNewBooks(offset, limit).getContent());
    }

    @ResponseBody
    @GetMapping("/books/popular")
    public BooksPageDto getPopularBooksPage(@RequestParam Integer offset, @RequestParam Integer limit) {
        return new BooksPageDto(bookService.getPageOfPopularBooks(offset, limit).getContent());
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) throws EmptySearchException {
        if (searchWordDto != null) {
            model.addAttribute("searchWordDto", searchWordDto);
            model.addAttribute("searchResults",
                    bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
            return "/search/index";
        } else {
            throw new EmptySearchException("Search impossible for null");
        }
    }

    @ResponseBody
    @GetMapping("/search/page/{searchWord}")
    public BooksPageDto getNextPage(@RequestParam Integer offset,
                                    @RequestParam Integer limit,
                                    @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService
                .getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }
}
