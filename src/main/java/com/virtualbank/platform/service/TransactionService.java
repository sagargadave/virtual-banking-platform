package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.TransactionHistoryResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionHistoryResponseDto>
    getTransactionHistory(String accountNumber);

    List<TransactionHistoryResponseDto> getMiniStatement(String accountNumber);
}