package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.Genres;
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

    @Query("FROM Book")
    List<Book> customFindAllBooks();

    //new book repo commands

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("FROM Book ORDER BY pubDate")
    List<Book> findBooksByPubDate(Pageable pageable);

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellers();

    @Query("FROM Book WHERE isBestseller = 1")
    List<Book> getBestsellersPage(Pageable pageable);

    @Query(value = "SELECT * FROM books " +
            "WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();
    
    List<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

//    @Query( "FROM Book " +
//            "LEFT JOIN genres ON books.id = genres.book_id " +
//            "WHERE genres.name = (:genre)")
//    List<Book> getBooksByGenre(@Param("genre") String genre, Pageable nextPage);
}
