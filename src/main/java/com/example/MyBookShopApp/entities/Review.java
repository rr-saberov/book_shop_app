package com.example.MyBookShopApp.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String review;

    private int book_id;

    public Review(String review, int book_id) {
        this.review = review;
        this.book_id = book_id;
    }
}
