package com.virtualbank.platform.repository;

import com.virtualbank.platform.entity.Account;
import com.virtualbank.platform.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction>
    findByFromAccount_AccountNumberOrToAccount_AccountNumber(
            String fromAccount,
            String toAccount
    );

    List<Transaction>
    findTop5ByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTransactionDateDesc(
            String fromAccount,
            String toAccount
    );

    List<Transaction> findByFromAccountAndTransactionDateBetween(
            Account account,
            LocalDateTime fromDate,
            LocalDateTime toDate
    );
}