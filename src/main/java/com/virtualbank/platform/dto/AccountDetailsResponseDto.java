package com.virtualbank.platform.dto;

import com.virtualbank.platform.enums.AccountStatus;
import com.virtualbank.platform.enums.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountDetailsResponseDto {

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private AccountStatus status;
}