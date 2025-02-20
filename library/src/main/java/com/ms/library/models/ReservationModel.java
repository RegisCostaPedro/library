package com.ms.library.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.library.enums.StatusReservation;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_RESERVATION")
public class ReservationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reservationId;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime reservationDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime returnDate;

    private StatusReservation status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookModel bookModel;

    public ReservationModel() {
    }

    public ReservationModel(UUID reservationId, LocalDateTime reservationDate, LocalDateTime returnDate, StatusReservation status, UserModel userModel, BookModel bookModel) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.returnDate = returnDate;
        this.status = status;
        this.userModel = userModel;
        this.bookModel = bookModel;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ReservationModel{" +
                "reservationId=" + reservationId +
                ", reservationDate=" + reservationDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", userModel=" + userModel +
                ", bookModel=" + bookModel +
                '}';
    }
}
