package com.udemy.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.loans.dto.LoanDto;
import com.udemy.loans.dto.LoansInfoDto;
import com.udemy.loans.service.ILoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private ILoanService loanService;

    @Value("${build.version}")
    private String buildInfo;

    @Autowired
    private LoansInfoDto loansInfoDto;

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

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(this.buildInfo);
    }

    @GetMapping("/loans-info")
    public ResponseEntity<LoansInfoDto> getLoansInfo() {
        return ResponseEntity.ok(this.loansInfoDto);
    }

}
