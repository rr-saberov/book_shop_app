package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    List<Book> getBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> getBooksByTitleContaining(String bookTitle);

    List<Book> getBooksByPriceBetween(Integer min, Integer max);

    List<Book> getBookByTitleContaining(String bookTitle, Pageable nextPage);

    List<Book> findAllByOrderByAuthor();

    List<Book> findAllByOrderByTag();

    List<Book> findAllByTagTagName(String tag);

    List<Book> findAllByOrderByGenreName();

    List<Book> findAllByGenreName(String genre);

    @Query("FROM Book ORDER BY pubDate")
    List<Book> findBooksByPubDate(Pageable pageable);

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellers();

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellersPage(Pageable pageable);

    @Query(value = "SELECT * FROM books " +
            "WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Author au " +
            "ORDER BY au.lastName")
    List<Book> getBooksOrderByAuthor(Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "WHERE b.author.lastName = :authorName")
    List<Book> getBooksByAuthorLastName(@Param("authorName") String authorName, Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "ORDER BY b.genre.name")
    List<Book> getBooksOrderByGenre();

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN Genres ge " +
            "WHERE ge.name = :genreName")
    List<Book> getBookPageByGenreName(@Param("genreName") String genre, Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "ORDER BY b.tag.tagName")
    List<Book> getBooksOrderByTag();

    @Query("SELECT b " +
            "FROM Book b " +
            "ORDER BY b.tag.tagName")
    List<Book> getBooksPageOrderByTag(Pageable nextPage);

    @Query("SELECT b " +
            "FROM Book b " +
            "WHERE b.tag.tagName = :tagName")
    List<Book> getBooksByTagName(@Param("tagName") String tag, Pageable nextPage);

}
