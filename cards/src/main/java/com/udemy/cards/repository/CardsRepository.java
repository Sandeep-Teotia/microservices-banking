package com.udemy.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.cards.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Integer> {

    Optional<Cards> findByMobileNumber(String mobileNumber);
}
