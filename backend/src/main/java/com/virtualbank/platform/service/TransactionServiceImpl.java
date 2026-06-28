package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.TransactionHistoryResponseDto;
import com.virtualbank.platform.entity.Transaction;
import com.virtualbank.platform.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionHistoryResponseDto>
    getTransactionHistory(String accountNumber) {

        List<Transaction> transactions =
                transactionRepository.findByFromAccount_AccountNumberOrToAccount_AccountNumber(accountNumber, accountNumber);

        return transactions.stream()
                .map(transaction ->
                        TransactionHistoryResponseDto.builder()
                                .transactionId(transaction.getTransactionId())
                                .amount(transaction.getAmount())
                                .transactionType(transaction.getTransactionType())
                                .description(transaction.getDescription())
                                .transactionDate(transaction.getTransactionDate())
                                .build()
                )
                .toList();
    }

    @Override
    public List<TransactionHistoryResponseDto>
    getMiniStatement(String accountNumber) {

        List<Transaction> transactions =
                transactionRepository.findTop5ByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTransactionDateDesc(
                                accountNumber,
                                accountNumber
                        );

        return transactions.stream()
                .map(transaction ->
                        TransactionHistoryResponseDto.builder()
                                .transactionId(transaction.getTransactionId())
                                .amount(transaction.getAmount())
                                .transactionType(transaction.getTransactionType())
                                .description(transaction.getDescription())
                                .transactionDate(transaction.getTransactionDate())
                                .build()
                )
                .toList();
    }
}