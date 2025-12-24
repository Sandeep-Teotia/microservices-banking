package com.udemy.cards.mapper;

import com.udemy.cards.dto.CardsDto;
import com.udemy.cards.entity.Cards;

public class CardsMapper {

    private CardsMapper() {
        // restrict instantiation
    }

    public static CardsDto mapToCardsDto(Cards cards) {
        return new CardsDto(cards.getMobileNumber(), cards.getCardNumber(), cards.getCardType(), cards.getTotalLimit(),
                cards.getAmountUsed(), cards.getAvailableAmount());
    }

    public static Cards mapToCards(CardsDto cardsDto) {
        Cards card = new Cards();
        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setCardType(cardsDto.getCardType());
        card.setTotalLimit(cardsDto.getTotalLimit());
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setAvailableAmount(cardsDto.getAvailableAmount());
        return card;
    }
}
