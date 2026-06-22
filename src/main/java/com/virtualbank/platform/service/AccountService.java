package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.AccountDetailsResponseDto;
import com.virtualbank.platform.dto.DepositRequestDto;
import com.virtualbank.platform.dto.TransferRequestDto;
import com.virtualbank.platform.dto.WithdrawRequestDto;

public interface AccountService {

    AccountDetailsResponseDto getAccountDetails(String accountNumber);

    String deposit(DepositRequestDto request);

    String transfer(TransferRequestDto request);

    String withdraw(WithdrawRequestDto request);
}