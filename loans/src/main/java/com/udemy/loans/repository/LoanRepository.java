package com.udemy.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.loans.entity.Loans;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);
}
