package com.virtualbank.platform.controller;

import com.virtualbank.platform.dto.*;
import com.virtualbank.platform.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/my-account")
    public AccountDetailsResponseDto getMyAccount(
            Authentication authentication) {

        String email = authentication.getName();

        return accountService.getAccountByEmail(email);
    }

    @GetMapping("/profile")
    public ProfileResponseDto getProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return accountService.getProfile(email);
    }

    @PostMapping("/change-password")
    public String changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordRequestDto request) {

        String email = authentication.getName();

        return accountService.changePassword(
                email,
                request);
    }

    @GetMapping("/statement")
    public ResponseEntity<byte[]> generateStatement(
            Authentication authentication,
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        String email = authentication.getName();

        byte[] pdf =
                accountService.generateStatement(
                        email,
                        fromDate,
                        toDate
                );

        return ResponseEntity.ok()
                .header(
                        "Content-Disposition",
                        "attachment; filename=statement.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }
}