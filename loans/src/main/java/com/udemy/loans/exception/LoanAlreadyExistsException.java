package com.udemy.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException {
    public LoanAlreadyExistsException(String mobileNumber) {
        super(String.format("Loan already exists for mobile number : '%s'", mobileNumber));
    }
}
