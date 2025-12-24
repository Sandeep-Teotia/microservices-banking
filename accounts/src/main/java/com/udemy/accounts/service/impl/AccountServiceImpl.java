package com.udemy.accounts.service.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.udemy.accounts.constants.AccountsConstant;
import com.udemy.accounts.dto.AccountsDto;
import com.udemy.accounts.dto.CustomerDto;
import com.udemy.accounts.entity.Accounts;
import com.udemy.accounts.entity.Customer;
import com.udemy.accounts.exception.CustomerAlreadyExistsException;
import com.udemy.accounts.exception.ResourceNotFoundException;
import com.udemy.accounts.mapper.AccountsMapper;
import com.udemy.accounts.mapper.CustomerMapper;
import com.udemy.accounts.repository.AccountsRepository;
import com.udemy.accounts.repository.CustomerRepository;
import com.udemy.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    @SuppressWarnings("null")
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with mobile number " + customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddress(AccountsConstant.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Override
    @SuppressWarnings("null")
    public boolean updateAccount(CustomerDto customerDto) {
        Optional<Accounts> accounts = accountsRepository.findById(customerDto.getAccountsDto().getAccountNumber());
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts", "accountNumber",
                    customerDto.getAccountsDto().getAccountNumber().toString());
        }
        Accounts account = accounts.get();
        account.setAccountType(customerDto.getAccountsDto().getAccountType());
        account.setBranchAddress(customerDto.getAccountsDto().getBranchAddress());
        accountsRepository.save(account);

        Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerId", account.getCustomerId().toString()));
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customerRepository.save(customer);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        customerRepository.deleteById(Objects.requireNonNull(customer.getCustomerId()));
        accountsRepository.deleteByCustomerId(Objects.requireNonNull(customer.getCustomerId()));
        return true;
    }

}
