package com.ms.library.services;

import com.ms.library.models.BookModel;
import com.ms.library.models.ClassModel;
import com.ms.library.models.LoanModel;
import com.ms.library.models.UserModel;
import com.ms.library.repositories.BookRepository;
import com.ms.library.repositories.LoanRepository;
import com.ms.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public LoanModel saveLoan(LoanModel loan){

        BookModel bookModel = bookRepository.findById(loan.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(loan.getUserModel().getUserId()).get();


        loan.setLoanDate(LocalDateTime.now());
        loan.setBookModel(bookModel);
        loan.setUserModel(userModel);

        return loanRepository.save(loan);
    }

    public List<LoanModel> findAllLoans(){
        return loanRepository.findAll();
    }

    public LoanModel findLoanById(UUID id){
        return loanRepository.findById(id).get();
    }

    public LoanModel updateLoan(LoanModel loan,UUID id){

        var loanFind = loanRepository.findById(id).get();
        BookModel bookModel = bookRepository.findById(loan.getBookModel().getBookId()).get();
        UserModel userModel = userRepository.findById(loan.getUserModel().getUserId()).get();


        loanFind.setLoanDate(LocalDateTime.now());
        loanFind.setReturnDate(loan.getReturnDate());
        loanFind.setBookModel(bookModel);
        loanFind.setUserModel(userModel);
        loanFind.setStatus(loan.getStatus());

        return loanRepository.save(loanFind);
    }

    public void deleteLoan(UUID id){
         loanRepository.deleteById(id);
    }
}
