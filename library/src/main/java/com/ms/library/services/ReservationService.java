package com.ms.library.services;

import com.ms.library.enums.StatusLoan;
import com.ms.library.enums.StatusReservation;
import com.ms.library.exceptions.NoBooksAvailableException;
import com.ms.library.models.BookModel;
import com.ms.library.models.LoanModel;
import com.ms.library.models.ReservationModel;
import com.ms.library.models.UserModel;
import com.ms.library.repositories.BookRepository;
import com.ms.library.repositories.LoanRepository;
import com.ms.library.repositories.ReservationRepository;
import com.ms.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    public ReservationModel saveReservation(ReservationModel reservationModel) {

        BookModel bookModel = bookRepository.findById(reservationModel.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(reservationModel.getUserModel().getUserId()).get();

        if (bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }

        var loan = new LoanModel();
        loan.setLoanId(reservationModel.getReservationId());
        loan.setLoanDate(reservationModel.getReservationDate());
        loan.setReturnDate(reservationModel.getReturnDate());
        loan.setUserModel(userModel);
        loan.setBookModel(bookModel);
        loan.setStatus(StatusLoan.RESERVED);

        loanService.saveLoan(loan);

        reservationModel.setBookModel(bookModel);
        reservationModel.setUserModel(userModel);
        reservationModel.setStatus(StatusReservation.ACTIVE);
        return reservationRepository.save(reservationModel);
    }

    public List<ReservationModel> findAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationModel findReservationById(UUID id) {
        return reservationRepository.findById(id).get();
    }

    public ReservationModel updateReservation(ReservationModel reservationModel, UUID id) {
        var reservationFind = reservationRepository.findById(id).get();
        BookModel bookModel = bookRepository.findById(reservationModel.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(reservationModel.getUserModel().getUserId()).get();

        if (bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }
        var loan = new LoanModel();


        reservationFind.setStatus(reservationModel.getStatus());
        reservationFind.setUserModel(userModel);
        reservationFind.setBookModel(bookModel);

        if (reservationModel.getReservationDate() == null) {
            var previousReservationDate = reservationRepository.findById(id).get();
            reservationFind.setReservationDate(previousReservationDate.getReservationDate());
        } else {
            reservationFind.setReservationDate(reservationModel.getReservationDate());
        }
        return reservationRepository.save(reservationFind);
    }

    public void deleteReservation(UUID id) {
        reservationRepository.deleteById(id);
    }
}
