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

    @Query("FROM Book")
    List<Book> customFindAllBooks();

    List<Book> getBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> getBooksByTitleContaining(String bookTitle);

    List<Book> getBooksByPriceBetween(Integer min, Integer max);

    List<Book> getBooksByPriceOldIs(Integer price);

    List<Book> getBookByTitleContaining(String bookTitle, Pageable nextPage);

    @Query("FROM Book ORDER BY pubDate")
    List<Book> findBooksByPubDate(Pageable pageable);

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellers();

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellersPage(Pageable pageable);

    @Query(value = "SELECT * FROM books " +
            "WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Author au " +
            "ORDER BY au.lastName")
    List<Book> getBooksOrderByAuthor(Pageable nextPage);

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Genres gen " +
            "ORDER BY gen.name")
    List<Book> getBooksOrderByGenre(Pageable nextPage);

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Tag tag " +
            "ORDER BY tag.tagName")
    List<Book> getBooksOrderByTag(Pageable nextPage);

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Author au " +
            "WHERE au.lastName = :authorName")
    List<Book> getBooksByAuthorLastName(@Param("authorName") String authorName, Pageable nextPage);

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Genres ge "
            + "WHERE ge.name = :genreName")
    List<Book> getBooksByGenreName(@Param("genreName") String genre, Pageable nextPage);

    @Query("SELECT b.title " +
            "FROM Book b " +
            "JOIN Tag tag " +
            "WHERE tag.tagName = :tagName")
    List<Book> getBooksByTagName(@Param("tagName") String tag, Pageable nextPage);


}
