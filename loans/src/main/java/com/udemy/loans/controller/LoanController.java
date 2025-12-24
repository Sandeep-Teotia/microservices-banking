package com.udemy.loans.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.loans.dto.LoanDto;
import com.udemy.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final ILoanService loanService;

    @GetMapping("/{mobileNumber}")
    public LoanDto getLoanDetails(@PathVariable String mobileNumber) {
        return loanService.getLoanDetails(mobileNumber);
    }

    @PostMapping("/{mobileNumber}")
    public LoanDto createLoanDetails(@PathVariable String mobileNumber) {
        return loanService.createLoanDetails(mobileNumber);
    }

    @PutMapping
    public boolean updateLoanDetails(@RequestBody LoanDto loanDto) {
        return loanService.updateLoanDetails(loanDto);
    }

    @DeleteMapping("/{mobileNumber}")
    public boolean deleteLoanDetails(@PathVariable String mobileNumber) {
        return loanService.deleteLoanDetails(mobileNumber);
    }

}
