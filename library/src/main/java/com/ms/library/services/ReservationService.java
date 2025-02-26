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
import java.util.Objects;
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
    @Autowired
    private BookService bookService;

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

    public ReservationModel updateReservation(ReservationModel reservationModel, UUID reservationId, UUID loanId) {

        // Procurando as devidas entidades no banco de dados
        var reservationFind = reservationRepository.findById(reservationId).get();
        var bookModel = bookRepository.findById(reservationModel.getBookModel().getBookId()).get();
        var userModel = userRepository.findById(reservationModel.getUserModel().getUserId()).get();
        var loan = loanService.findLoanById(loanId);



        if (bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }
        // Alterando os dados do empréstimo de acordo com a os dados da reserva
        loan.setLoanDate(reservationModel.getReservationDate());
        loan.setReturnDate(reservationModel.getReturnDate());
        loan.setUserModel(userModel);
        loan.setBookModel(bookModel);

        switch (reservationModel.getStatus()) {
            case CANCELLED:
                loan.setStatus(StatusLoan.RETURNED);
                bookService.updateBookQuantityByRole(bookModel.getBookId(),userModel.getRoleUser(),reservationModel.getBookModel().getQuantity_available(),loan.getStatus());
                loanService.deleteLoan(loanId);
                return reservationFind;

            case ACTIVE:
                loan.setStatus(StatusLoan.RESERVED);
                break;
        }

        loanService.updateLoan(loan, loanId);

        if (reservationModel.getReservationDate() == null) {
            var previousReservationDate = reservationRepository.findById(reservationId).get();
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
