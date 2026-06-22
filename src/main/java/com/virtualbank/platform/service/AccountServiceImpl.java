package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.AccountDetailsResponseDto;
import com.virtualbank.platform.dto.DepositRequestDto;
import com.virtualbank.platform.dto.TransferRequestDto;
import com.virtualbank.platform.dto.WithdrawRequestDto;
import com.virtualbank.platform.entity.Account;
import com.virtualbank.platform.exception.ResourceNotFoundException;
import com.virtualbank.platform.repository.AccountRepository;
import com.virtualbank.platform.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.virtualbank.platform.entity.Transaction;
import com.virtualbank.platform.enums.TransactionType;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public AccountDetailsResponseDto getAccountDetails(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"
                        ));

        return AccountDetailsResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    @Override
    public String deposit(DepositRequestDto request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(
                account.getBalance().add(request.getAmount())
        );

        accountRepository.save(account);

        return "Amount Deposited Successfully";
    }

    @Override
    public String transfer(TransferRequestDto request) {

        Account fromAccount = accountRepository
                .findByAccountNumber(request.getFromAccount())
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));

        Account toAccount = accountRepository
                .findByAccountNumber(request.getToAccount())
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        if (fromAccount.getBalance()
                .compareTo(request.getAmount()) < 0) {

            return "Insufficient Balance";
        }

        fromAccount.setBalance(
                fromAccount.getBalance()
                        .subtract(request.getAmount())
        );

        toAccount.setBalance(
                toAccount.getBalance()
                        .add(request.getAmount())
        );

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .amount(request.getAmount())
                .transactionType(TransactionType.TRANSFER)
                .description("Money Transfer")
                .transactionDate(LocalDateTime.now())
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .build();

        transactionRepository.save(transaction);

        return "Transfer Successful";
    }

    @Override
    public String withdraw(WithdrawRequestDto request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        if (account.getBalance()
                .compareTo(request.getAmount()) < 0) {

            return "Insufficient Balance";
        }

        account.setBalance(
                account.getBalance()
                        .subtract(request.getAmount())
        );

        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .amount(request.getAmount())
                .transactionType(TransactionType.WITHDRAW)
                .description("Money Withdrawal")
                .transactionDate(LocalDateTime.now())
                .fromAccount(account)
                .build();

        transactionRepository.save(transaction);

        return "Amount Withdrawn Successfully";
    }
}