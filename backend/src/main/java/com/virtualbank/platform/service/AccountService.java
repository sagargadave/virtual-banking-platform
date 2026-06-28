package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.*;

public interface AccountService {

    AccountDetailsResponseDto getAccountDetails(String accountNumber);

    String deposit(DepositRequestDto request);

    String transfer(TransferRequestDto request);

    String withdraw(WithdrawRequestDto request);

    AccountDetailsResponseDto getAccountByEmail(String email);

    ProfileResponseDto getProfile(String email);

    String updateProfile(String email, UpdateProfileRequestDto request);

    String changePassword(String email, ChangePasswordRequestDto request);

    byte[] generateStatement(
            String email,
            String fromDate,
            String toDate
    );
}