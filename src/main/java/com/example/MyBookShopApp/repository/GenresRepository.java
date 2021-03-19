package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {
}
