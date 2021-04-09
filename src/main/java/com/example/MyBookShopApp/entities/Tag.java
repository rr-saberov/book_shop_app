package com.example.MyBookShopApp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private int id;
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<Book> bookList;

}
