package com.example.MyBookShopApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
@NoArgsConstructor
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author")
    List<Book> bookList = new ArrayList<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
