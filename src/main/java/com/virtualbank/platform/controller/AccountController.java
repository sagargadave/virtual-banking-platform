package com.virtualbank.platform.controller;

import com.virtualbank.platform.dto.AccountDetailsResponseDto;
import com.virtualbank.platform.dto.DepositRequestDto;
import com.virtualbank.platform.dto.TransferRequestDto;
import com.virtualbank.platform.dto.WithdrawRequestDto;
import com.virtualbank.platform.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/details/{accountNumber}")
    public AccountDetailsResponseDto getAccountDetails(
            @PathVariable String accountNumber) {

        return accountService.getAccountDetails(accountNumber);
    }

    @PostMapping("/deposit")
    public String deposit(
            @RequestBody DepositRequestDto request) {

        return accountService.deposit(request);
    }

    @PostMapping("/transfer")
    public String transfer(
            @RequestBody TransferRequestDto request) {

        return accountService.transfer(request);
    }

    @PostMapping("/withdraw")
    public String withdraw(
            @RequestBody WithdrawRequestDto request) {

        return accountService.withdraw(request);
    }
}