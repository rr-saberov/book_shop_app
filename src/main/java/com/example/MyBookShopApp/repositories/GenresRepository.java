package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Genres;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {

    @Query("SELECT b " +
            "FROM Book b " +
            "ORDER BY b.genre.name")
    List<Book> getBooksOrderByGenre();

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Genres ge " +
            "WHERE ge.name = :genreName")
    List<Book> getBooksByGenreName(@Param("genreName") String genre, Pageable nextPage);

}
