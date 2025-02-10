package com.ms.library.models;


import com.ms.library.enums.StatusReservation;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_RESERVATION")
public class ReservationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reservationId;

    private LocalDateTime reservationDate;

    private StatusReservation status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book")
    private BookModel book;

    public ReservationModel() {
    }

    public ReservationModel(UUID reservationId, LocalDateTime reservationDate, StatusReservation status, UserModel user, BookModel book) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.status = status;
        this.user = user;
        this.book = book;
    }

    public UUID getId_reservation() {
        return reservationId;
    }

    public void setId_reservation(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getReservation_date() {
        return reservationDate;
    }

    public void setReservation_date(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
    }

    public UserModel getUser_id() {
        return user;
    }

    public void setUser_id(UserModel user) {
        this.user = user;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }
}
