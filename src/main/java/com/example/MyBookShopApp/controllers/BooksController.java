package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Review;
import com.example.MyBookShopApp.repositories.BookRepository;
import com.example.MyBookShopApp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books/")
public class BooksController {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final ResourceStorage storage;

    @Autowired
    public BooksController(BookRepository bookRepository, ReviewRepository reviewRepository, ResourceStorage storage) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.storage = storage;
    }

    @GetMapping("{slug}")
    public String bookPage(@PathVariable String slug, Model model) {
        Book book = bookRepository.findBookBySlug(slug);
        model.addAttribute("slugBook", book);
        return "/books/slug";
    }

    @PostMapping("{slug}/img/save")
    public String saveNewBookImage(@RequestParam MultipartFile file,
                                   @PathVariable String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Book bookToUpdate = bookRepository.findBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate);
        return ("redirect:/books/" + slug);
    }

    @GetMapping("download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file date length: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }
    
    @PostMapping("{slug}/review")
    public String addReview(@PathVariable("slug") String slug, String review) {
        reviewRepository.save(new Review(review, bookRepository.findBookBySlug(slug).getId()));
        return "redirect:/books/" + slug;
    }

    @PostMapping("{slug}/updateRating")
    public String updateRating(@PathVariable("slug") String slug, Double rating) {
        Book bookToUpdate = bookRepository.findBookBySlug(slug);
        bookToUpdate.setRating(rating);
        bookRepository.save(bookToUpdate);
        return "redirect:/books/" + slug;
    }
}
