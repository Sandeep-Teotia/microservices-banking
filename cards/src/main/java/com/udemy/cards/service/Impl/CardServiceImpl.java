package com.udemy.cards.service.Impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.cards.constans.CardsConstants;
import com.udemy.cards.dto.CardsDto;
import com.udemy.cards.entity.Cards;
import com.udemy.cards.exception.ResourceNotFoundException;
import com.udemy.cards.mapper.CardsMapper;
import com.udemy.cards.repository.CardsRepository;
import com.udemy.cards.service.ICardsService;

@Service
public class CardServiceImpl implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public CardsDto createCard(String mobileNumber) {
        Cards newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
        return CardsMapper.mapToCardsDto(newCard);
    }

    @Override
    @Transactional
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.delete(card);
        return true;
    }

    @Override
    public CardsDto getCardDetails(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("card", "Mobile Number", mobileNumber));
        return CardsMapper.mapToCardsDto(card);
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("card", "Mobile Number", cardsDto.getMobileNumber()));
        card.setTotalLimit(cardsDto.getTotalLimit());
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setAvailableAmount(cardsDto.getAvailableAmount());
        cardsRepository.save(card);
        return true;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

}
