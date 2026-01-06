package com.udemy.accounts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.accounts.constants.AccountsConstant;
import com.udemy.accounts.dto.AccountsConfigInfoDto;
import com.udemy.accounts.dto.CustomerDto;
import com.udemy.accounts.dto.ResponseDto;
import com.udemy.accounts.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/", produces = MediaType.APPLICATION_JSON_VALUE)
@RefreshScope
public class AccountsController {

    private IAccountService iAccountService;

    @Value("${build.version}")
    private String buildVersion;

    private Environment environment;

    private AccountsConfigInfoDto accountsConfigInfoDto;

    AccountsController(IAccountService iAccountService, Environment environment,
            AccountsConfigInfoDto accountsConfigInfoDto) {
        this.iAccountService = iAccountService;
        this.environment = environment;
        this.accountsConfigInfoDto = accountsConfigInfoDto;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }

    @GetMapping("/fetch/{mobileNumber}")
    public ResponseEntity<CustomerDto> fetchAccountByMobileNumber(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(iAccountService.fetchAccountByMobileNumber(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
        iAccountService.updateAccount(customerDto);
        return ResponseEntity.ok(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
    }

    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable String mobileNumber) {
        iAccountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> buildInfo() {
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        String apiKey = environment.getProperty("GOOGLE_API_KEY");
        if (apiKey == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API Key not found");
        }
        return ResponseEntity.ok(apiKey);
    }

    @GetMapping("/account-info")
    public ResponseEntity<AccountsConfigInfoDto> getAccountInfo() {
        return ResponseEntity.ok(accountsConfigInfoDto);
    }

}
