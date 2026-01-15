package com.udemy.accounts.dto;

import lombok.Data;

@Data
public class CustomerDetailsDto {
    private CustomerDto customerDto;
    private AccountsDto accountsDto;
    private CardsDto cardsDto;
    private LoanDto loanDto;

}
