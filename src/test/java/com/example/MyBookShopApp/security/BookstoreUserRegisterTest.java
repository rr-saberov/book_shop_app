package com.example.MyBookShopApp.security;

import lombok.RequiredArgsConstructor;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class BookstoreUserRegisterTest {

    private final BookstoreUserRegister userRegister;
    private final PasswordEncoder passwordEncoder;
    private RegistrationForm registrationForm;

    @MockBean
    private BookstoreUserRepository bookstoreUserRepositoryMock;

    @BeforeEach
    void setUp() {
        registrationForm = new RegistrationForm();
        registrationForm.setEmail("test@mail.org");
        registrationForm.setName("Test");
        registrationForm.setPass("test1");
        registrationForm.setPhone("9946565423");
    }

    @AfterEach
    void tearDown() {
        registrationForm = null;
    }

    @Test
    void registerNewUser() {
        BookstoreUser user = userRegister.registerNewUser(registrationForm);
        assertNotNull(user);
        assertTrue(passwordEncoder.matches(registrationForm.getPass(), user.getPassword()));
        assertTrue(CoreMatchers.is(user.getPhone()).matches(registrationForm.getPhone()));
        assertTrue(CoreMatchers.is(user.getName()).matches(registrationForm.getName()));
        assertTrue(CoreMatchers.is(user.getEmail()).matches(registrationForm.getEmail()));

        Mockito.verify(bookstoreUserRepositoryMock, Mockito.times(1))
                .save(Mockito.any(BookstoreUser.class));
    }

    @Test
    void registerNewUserFail() {
        Mockito.doReturn(new BookstoreUser())
                .when(bookstoreUserRepositoryMock)
                .findBookstoreUserByEmail(registrationForm.getEmail());

        BookstoreUser user = userRegister.registerNewUser(registrationForm);
        assertNull(user);
    }
}