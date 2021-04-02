package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.SearchWordDto;
import com.example.MyBookShopApp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("booksByTag")
    public List<Book> booksByTag() {
        return tagService.getBooksOrderByTag();
    }

    @GetMapping
    public String tagsPage() {
        return "tags/index";
    }

    @ResponseBody
    @GetMapping("books/tags/{tag}")
    public String getBooksPageByTags(@PathVariable(value = "tag", required = false) SearchWordDto searchWordDto,
                                 Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                tagService.getBooksWithTag(searchWordDto.getExample(), 0, 5).getContent());
        return "/tags/index";
    }
}
