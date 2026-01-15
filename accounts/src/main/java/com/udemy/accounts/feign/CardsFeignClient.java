package com.udemy.accounts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.udemy.accounts.dto.CardsDto;

@FeignClient(name = "cards")
public interface CardsFeignClient {

    @GetMapping("/cards/{mobileNumber}")
    CardsDto getCardDetails(@PathVariable String mobileNumber);
}
