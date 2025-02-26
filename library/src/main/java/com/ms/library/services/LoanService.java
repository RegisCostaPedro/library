package com.ms.library.services;

import com.ms.library.enums.RoleUser;
import com.ms.library.enums.StatusLoan;
import com.ms.library.exceptions.*;
import com.ms.library.models.BookModel;
import com.ms.library.models.LoanModel;
import com.ms.library.models.UserModel;
import com.ms.library.repositories.BookRepository;
import com.ms.library.repositories.LoanRepository;
import com.ms.library.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.UUID;


@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Transactional
    public LoanModel saveLoan(LoanModel loan) {

        BookModel bookModel = bookRepository.findById(loan.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(loan.getUserModel().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }

        if (loan.getReturnDate() == null) {
            LocalDateTime returnDateIfNull = LocalDateTime.now().plusDays(15);
            loan.setReturnDate(returnDateIfNull);
        }


        List<LoanModel> loansWithStatusInUse = loanRepository.checkBookStatus(bookModel.getBookId());
        if (loansWithStatusInUse.stream().anyMatch(loanModel -> loanModel.getStatus() == StatusLoan.IN_USE) && bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }
        Long loanQuantity = loanRepository.countLoans(userModel.getUserId());

        if (userModel.getRoleUser().equals(RoleUser.STUDENT) && loanQuantity == 1) {
            throw new LoanQuantityByStudentException();
        }
        validReturnDate(loan, loan.getReturnDate());
        if (loan.getStatus() == null) {
            loan.setStatus(StatusLoan.IN_USE);
        }
        loan.setStatus(StatusLoan.RESERVED);

        loan.setLoanDate(LocalDateTime.now());
        loan.setBookModel(bookModel);
        loan.setUserModel(userModel);

        Integer bookQuantity = (loan.getBookQuantity() == null) ? 1 : loan.getBookQuantity();

        bookService.updateBookQuantityByRole(bookModel.getBookId(),
                userModel.getRoleUser(), bookQuantity, loan.getStatus());

        loan.setBookQuantity(bookQuantity);
        return loanRepository.save(loan);
    }

    public List<LoanModel> findAllLoans() {
        return loanRepository.findAll();
    }

    public LoanModel findLoanById(UUID id) {
        return loanRepository.findById(id).get();
    }

    public LoanModel updateLoan(LoanModel loan, UUID id) {

        var loanFind = loanRepository.findById(id).get();
        BookModel bookModel = bookRepository.findById(loan.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(loan.getUserModel().getUserId()).get();

        if (loanFind.getStatus().equals(StatusLoan.RETURNED)){
            throw new LoanDevolutionStatusException();
        }

        if (bookModel.getQuantity_available() <= 0) {
            throw new NoBooksAvailableException();
        }

        if (loan.getReturnDate() != null) {
            validReturnDate(loan, loan.getReturnDate());
            loanFind.setReturnDate(loan.getReturnDate());
        }

        bookService.updateBookQuantityByRole(bookModel.getBookId(), userModel.getRoleUser(), loanFind.getBookQuantity(), loan.getStatus());
        loanFind.setLoanDate(LocalDateTime.now());
        loanFind.setBookModel(bookModel);
        loanFind.setUserModel(userModel);
        loanFind.setStatus(loan.getStatus());

        return loanRepository.save(loanFind);


    }

    public void deleteLoan(UUID id) {
        loanRepository.deleteById(id);
    }

    public void validReturnDate(LoanModel loan, LocalDateTime returnDate) {
        BookModel bookModel = bookRepository.findById(loan.getBookModel().getBookId()).get();

        LocalDateTime loanDate = (loan.getLoanDate() != null) ? loan.getLoanDate() : LocalDateTime.now();

        long daysBetween = switch (loan.getStatus()) {
            case RESERVED -> ChronoUnit.DAYS.between(loanDate, returnDate);
            default -> ChronoUnit.DAYS.between(LocalDateTime.now(), returnDate);

        };

        if (daysBetween > 15) {
            throw new LoanReturnDateOutOfLimitException();
        }

        if (returnDate.isBefore(LocalDateTime.now())) {
            throw new InvalidReturnDateException();
        }
        loanRepository.checkBookStatus(bookModel.getBookId());

    }


}
