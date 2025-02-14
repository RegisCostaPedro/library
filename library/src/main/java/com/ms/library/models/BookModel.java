package com.ms.library.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BOOK")
public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;
    private String isbn;
    private String author;
    @Column(unique = true)
    private String title;
    private String description;
    private Integer quantityAvailable;
    private String publisher;
    private Integer yearPublication;
    private String genre;
    private String image;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<ReservationModel> reservations = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<LoanModel> loan_books = new HashSet<>();

    public BookModel() {
    }

    public BookModel(UUID bookId, String isbn, String author, String title, String description, Integer quantityAvailable, String publisher, Integer yearPublication, String genre, String image, Set<ReservationModel> reservations, Set<LoanModel> loan_books) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.genre = genre;
        this.image = image;
        this.reservations = reservations;
        this.loan_books = loan_books;
    }

    public UUID getId_book() {
        return bookId;
    }

    public void setId_book(UUID bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity_available() {
        return quantityAvailable;
    }

    public void setQuantity_available(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear_publication() {
        return yearPublication;
    }

    public void setYear_publication(Integer yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public void setReservations(Set<ReservationModel> reservations) {
        this.reservations = reservations;
    }



    public void setLoan_books(Set<LoanModel> loan_books) {
        this.loan_books = loan_books;
    }
}
