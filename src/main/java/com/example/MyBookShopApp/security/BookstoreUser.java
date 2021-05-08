package com.example.MyBookShopApp.security;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class BookstoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;

}
