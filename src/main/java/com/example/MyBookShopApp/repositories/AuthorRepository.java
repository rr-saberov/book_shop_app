package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Author;
import com.example.MyBookShopApp.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Author au " +
            "ORDER BY au.lastName")
    List<Book> getBooksOrderByAuthor(Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Author au " +
            "WHERE au.lastName = :authorName")
    List<Book> getBooksByAuthorLastName(@Param("authorName") String authorName, Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Author  au " +
            "ORDER BY au.lastName")
    List<Book> getBooksOrderByAuthor();
}
