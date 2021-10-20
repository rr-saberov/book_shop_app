package com.example.MyBookShopApp.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test_entities")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
}
