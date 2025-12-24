package com.udemy.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 50, message = "Name should be between 5 and 50 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Mobile Number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number should be valid")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
