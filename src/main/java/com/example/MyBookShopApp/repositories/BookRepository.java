package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("SELECT book " +
            "FROM Book book " +
            "WHERE book.author.lastName = :name")
    List<Book> getBooksByAuthorLastNameContaining(@Param("name") String name);

    List<Book> getBooksByPriceBetween(Double min, Double max);

    List<Book> getBookByTitleContaining(String bookTitle);

    Page<Book> getBookPageByTitleContaining(String bookTitle, Pageable pageable);

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
            "WHERE price = (SELECT MAX(price) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

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
    List<Book> getBooksByTagName(@Param("tagName") String tag);

    Book findBookBySlug(String slug);

    List<Book> findBooksBySlugIn(String[] slug);

}
