package com.virtualbank.platform.dto;

import com.virtualbank.platform.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionHistoryResponseDto {

    private String transactionId;

    private BigDecimal amount;

    private TransactionType transactionType;

    private String description;

    private LocalDateTime transactionDate;
}