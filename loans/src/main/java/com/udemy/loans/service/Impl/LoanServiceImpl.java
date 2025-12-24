package com.udemy.loans.service.Impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.udemy.loans.constants.LoansConstants;
import com.udemy.loans.dto.LoanDto;
import com.udemy.loans.entity.Loans;
import com.udemy.loans.exception.LoanAlreadyExistsException;
import com.udemy.loans.exception.ResourseNotFoundException;
import com.udemy.loans.mapper.LoanMapper;
import com.udemy.loans.repository.LoanRepository;
import com.udemy.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public LoanDto getLoanDetails(String mobileNumber) {
        Optional<Loans> loan = loanRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            return LoanMapper.mapToLoanDto(loan.get(), new LoanDto());
        }
        throw new ResourseNotFoundException("Loan ", "mobileNumber", mobileNumber);
    }

    @Override
    public LoanDto createLoanDetails(String mobileNumber) {
        if (loanRepository.existsByMobileNumber(mobileNumber)) {
            throw new LoanAlreadyExistsException("Loan already exists for mobile number: " + mobileNumber);
        }
        LoanDto savedLoanDto = LoanMapper.mapToLoanDto(loanRepository.save(createNewLoan(mobileNumber)),
                new LoanDto());
        return savedLoanDto;
    }

    @Override
    public boolean updateLoanDetails(LoanDto loanDto) {
        boolean isUpdated = false;
        Optional<Loans> loan = loanRepository.findByMobileNumber(loanDto.getMobileNumber());
        if (loan.isPresent()) {
            loanRepository.save(LoanMapper.mapToLoan(loanDto, loan.get()));
            isUpdated = true;
        } else {
            throw new ResourseNotFoundException("Loan ", "mobileNumber", loanDto.getMobileNumber());
        }
        return isUpdated;
    }

    @Override
    public boolean deleteLoanDetails(String mobileNumber) {
        boolean isDeleted = false;
        Optional<Loans> loan = loanRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            loanRepository.delete(loan.get());
            isDeleted = true;
        } else {
            throw new ResourseNotFoundException("Loan ", "mobileNumber", mobileNumber);
        }
        return isDeleted;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
