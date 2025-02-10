package com.ms.library.models;

import com.ms.library.enums.StatusLoan;
import com.ms.library.enums.StatusReservation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_LOAN")
public class LoanModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loanId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookModel book;

    private LocalDateTime loanDate;

    private Date returnDate;

    private StatusLoan status;

    public LoanModel() {
    }

    public LoanModel(UUID loanId, UserModel user, BookModel book, LocalDateTime loanDate, Date returnDate, StatusLoan status) {
        this.loanId = loanId;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public UUID getId_loan() {
        return loanId;
    }

    public void setId_loan(UUID loanId) {
        this.loanId = loanId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public LocalDateTime getLoan_date() {
        return loanDate;
    }

    public void setLoan_date(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturn_date() {
        return returnDate;
    }

    public void setReturn_date(Date returnDate) {
        this.returnDate = returnDate;
    }

    public StatusLoan getStatus() {
        return status;
    }

    public void setStatus(StatusLoan status) {
        this.status = status;
    }
}
