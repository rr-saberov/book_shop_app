package com.example.MyBookShopApp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private int id;
    private String tagName;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;

}
