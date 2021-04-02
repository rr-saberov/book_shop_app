package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Genres;
import com.example.MyBookShopApp.repositories.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService {

    private GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genres> getAllGenres() {
        return genresRepository.findAll();
    }

    public List<Book> getBooksByGenre() {
        return genresRepository.getBooksOrderByGenre();
    }

    public Page<Book> getBooksPageByGenre(String genre, Integer page, Integer limit) {
        Pageable nextPage = PageRequest.of(page, limit);
        if (genre.isEmpty()) {
            return new PageImpl<>(genresRepository.getBooksOrderByGenre());
        } else {
            return new PageImpl<>(genresRepository.getBooksByGenreName(genre, nextPage));
        }
    }
}
