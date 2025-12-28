package com.udemy.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cards.dto.CardsDto;
import com.udemy.cards.dto.CardsInfoDto;
import com.udemy.cards.service.ICardsService;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private ICardsService cardsService;

    @Autowired
    private CardsInfoDto cardsInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/card-info")
    public ResponseEntity<CardsInfoDto> getCardInfo() {
        return ResponseEntity.ok(cardsInfoDto);
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(this.buildVersion);
    }

    @PostMapping("/{mobileNumber}")
    public CardsDto createCard(@PathVariable String mobileNumber) {
        return cardsService.createCard(mobileNumber);
    }

    @GetMapping("/{mobileNumber}")
    public CardsDto getCardDetails(@PathVariable String mobileNumber) {
        return cardsService.getCardDetails(mobileNumber);
    }

    @PutMapping
    public boolean updateCard(@RequestBody CardsDto cardsDto) {
        return cardsService.updateCard(cardsDto);
    }

    @DeleteMapping("/{mobileNumber}")
    public boolean deleteCard(@PathVariable String mobileNumber) {
        return cardsService.deleteCard(mobileNumber);
    }

}
