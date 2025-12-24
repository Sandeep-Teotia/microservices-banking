package com.udemy.cards.service;

import com.udemy.cards.dto.CardsDto;

public interface ICardsService {
    CardsDto createCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);

    CardsDto getCardDetails(String mobileNumber);
}
