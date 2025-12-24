package com.udemy.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
