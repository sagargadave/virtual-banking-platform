package com.virtualbank.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawRequestDto {

    private String accountNumber;

    private BigDecimal amount;
}