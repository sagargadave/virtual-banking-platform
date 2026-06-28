package com.virtualbank.platform.controller;

import com.virtualbank.platform.dto.TransactionHistoryResponseDto;
import com.virtualbank.platform.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/history/{accountNumber}")
    public List<TransactionHistoryResponseDto>
    getHistory(
            @PathVariable String accountNumber) {

        return transactionService.getTransactionHistory(accountNumber);
    }

    @GetMapping("/mini-statement/{accountNumber}")
    public List<TransactionHistoryResponseDto>
    getMiniStatement(
            @PathVariable String accountNumber) {

        return transactionService.getMiniStatement(accountNumber);
    }
}