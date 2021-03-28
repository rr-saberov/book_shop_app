package com.example.MyBookShopApp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@ApiModel("entity representing a book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id, generated auto")
    private Integer id;

    @Column(name = "pub_date")
    @ApiModelProperty("publish date")
    private Date pubDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ApiModelProperty("book author name")
    private Author author;

    @Column(name = "is_bestseller")
    @ApiModelProperty("if bestseller = 1")
    private Integer isBestseller;

    @ApiModelProperty("identity sequence of characters")
    private String slag;
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
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "genres_id", referencedColumnName = "id")
    private Genres genre;

    @OneToMany(mappedBy = "book")
    private List<Tag> tagList;

}
