package com.ms.library.controllers;

import com.ms.library.dtos.LoanRecordDto;
import com.ms.library.models.LoanModel;
import com.ms.library.services.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanModel> createLoan(@RequestBody LoanRecordDto loanRecordDto) {
        LoanModel loanModel = new LoanModel();

        System.out.println(loanRecordDto.bookModel().getBookId());
        System.out.println(loanRecordDto.userModel().getUserId());
        System.out.println(loanRecordDto.status());
        System.out.println(loanRecordDto.returnDate());
        System.out.println("-------------------------------------------------------");
        BeanUtils.copyProperties(loanRecordDto, loanModel);

        return new ResponseEntity<>(loanService.saveLoan(loanModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanModel>> findAllLoans() {
        return new ResponseEntity<>(loanService.findAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanModel> findLoanById(@PathVariable UUID id) {
        return new ResponseEntity<>(loanService.findLoanById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanModel> updateLoan(@RequestBody LoanRecordDto loanRecordDto, @PathVariable UUID id) {
        var loanModel = new LoanModel();

        BeanUtils.copyProperties(loanRecordDto, loanModel);
        return new ResponseEntity<>(loanService.updateLoan(loanModel, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteLoanById(@PathVariable UUID id) {
        loanService.deleteLoan(id);
        ResponseEntity.ok().body("Loan deleted with successfully");
    }

}
