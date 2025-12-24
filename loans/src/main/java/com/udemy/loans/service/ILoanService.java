package com.udemy.loans.service;

import com.udemy.loans.dto.LoanDto;

public interface ILoanService {
    public LoanDto getLoanDetails(String mobileNumber);

    public LoanDto createLoanDetails(String mobileNumber);

    public boolean updateLoanDetails(LoanDto loanDto);

    public boolean deleteLoanDetails(String mobileNumber);
}
