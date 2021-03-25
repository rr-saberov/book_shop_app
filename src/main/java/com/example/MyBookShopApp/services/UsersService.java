package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.entities.User;
import com.example.MyBookShopApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<User> getUsersData() {
        return userRepository.findAll();
    }
}
