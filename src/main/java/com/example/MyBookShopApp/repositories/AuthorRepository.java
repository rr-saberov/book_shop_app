package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a " +
            "FROM Author a " +
            "WHERE a.firstName = :name")
    Author getAuthorByFirstName(@Param("name") String name);
}
