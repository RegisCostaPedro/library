package com.ms.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.library.enums.StatusLoan;
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
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookModel bookModel;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime loanDate;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date returnDate;

    private StatusLoan status;

    public LoanModel() {
    }

    public LoanModel(UUID loanId, UserModel userModel, BookModel bookModel, LocalDateTime loanDate, Date returnDate, StatusLoan status) {
        this.loanId = loanId;
        this.userModel = userModel;
        this.bookModel = bookModel;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public void setLoanId(UUID loanId) {
        this.loanId = loanId;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public BookModel getBookModel() {
        return bookModel;
    }

    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public StatusLoan getStatus() {
        return status;
    }

    public void setStatus(StatusLoan status) {
        this.status = status;
    }
}
