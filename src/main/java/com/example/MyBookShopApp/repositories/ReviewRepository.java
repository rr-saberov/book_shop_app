package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
