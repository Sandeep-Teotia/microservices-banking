package com.udemy.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByCustomerId(Long customerId);

    @Transactional
    void deleteByCustomerId(Long customerId);
}
