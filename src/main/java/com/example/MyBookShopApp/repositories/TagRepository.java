package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {


    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Tag tag " +
            "ORDER BY tag.tagName")
    List<Book> getBooksOrderByTag();

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Tag tag " +
            "ORDER BY tag.tagName")
    List<Book> getBooksPageOrderByTag(Pageable nextPage);


    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Tag tag " +
            "WHERE tag.tagName = :tagName")
    List<Book> getBooksByTagName(@Param("tagName") String tag, Pageable nextPage);
}
