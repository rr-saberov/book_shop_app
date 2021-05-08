package com.example.MyBookShopApp.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "books")
@ApiModel("entity representing a book")
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id, generated auto")
    private Integer id;

    @Column(name = "pub_date")
    @ApiModelProperty("publish date")
    private Date pubDate;

    @Column(name = "is_bestseller")
    @ApiModelProperty("if bestseller = 1")
    private Integer isBestseller;

    @ApiModelProperty("identity sequence of characters")
    private String slug;

    @ApiModelProperty("book title")
    private String title;

    @ApiModelProperty("book image URL")
    private String image;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description test")
    private String description;

    @Column(name = "price")
    @JsonProperty("price")
    @ApiModelProperty("book price without discount")
    private Integer priceOld;

    @Column(name = "discount")
    @JsonProperty("discount")
    @ApiModelProperty("discount value")
    private Double price;

    @JsonProperty
    public Integer discountPrice() {
        return priceOld - Math.toIntExact(Math.round(price * priceOld));
    }

    @JsonGetter("authors")
    public String authorsFullName() {
        return author.toString();
    }

    @OneToMany(mappedBy = "book")
    private List<BookFile> bookFileList = new ArrayList<>();

/*
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
*/

    @ManyToOne
    @JoinColumn(name = "genres_id", referencedColumnName = "id")
    private Genres genre;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ApiModelProperty("book author name")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "tags_id",referencedColumnName = "id")
    private Tag tag;


}
