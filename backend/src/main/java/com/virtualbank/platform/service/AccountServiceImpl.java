package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.*;
import com.virtualbank.platform.entity.Account;
import com.virtualbank.platform.entity.User;
import com.virtualbank.platform.exception.ResourceNotFoundException;
import com.virtualbank.platform.repository.AccountRepository;
import com.virtualbank.platform.repository.TransactionRepository;
import com.virtualbank.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualbank.platform.entity.Transaction;
import com.virtualbank.platform.enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public AccountDetailsResponseDto getAccountByEmail(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Account account = accountRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        return AccountDetailsResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    @Override
    public ProfileResponseDto getProfile(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return ProfileResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    @Override
    public String updateProfile(
            String email,
            UpdateProfileRequestDto request) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        userRepository.save(user);

        return "Profile Updated Successfully";
    }

    @Override
    public String changePassword(
            String email,
            ChangePasswordRequestDto request) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean isCurrentPasswordCorrect =
                passwordEncoder.matches(
                        request.getCurrentPassword(),
                        user.getPassword());

        if (!isCurrentPasswordCorrect) {

            return "Current Password is Incorrect";
        }

        if (!request.getNewPassword()
                .equals(request.getConfirmPassword())) {

            return "New Password and Confirm Password do not match";
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        userRepository.save(user);

        return "Password Changed Successfully";
    }

    @Override
    public byte[] generateStatement(
            String email,
            String fromDate,
            String toDate) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Account account = accountRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        List<Transaction> transactions =
                transactionRepository
                        .findByFromAccountAndTransactionDateBetween(
                                account,
                                LocalDate.parse(fromDate)
                                        .atStartOfDay(),
                                LocalDate.parse(toDate)
                                        .atTime(23, 59, 59)
                        );

        ByteArrayOutputStream baos =
                new ByteArrayOutputStream();

        try {

            PdfWriter writer =
                    new PdfWriter(baos);

            PdfDocument pdf =
                    new PdfDocument(writer);

            Document document =
                    new Document(pdf);

            document.add(
                    new Paragraph(
                            "Virtual Banking Platform"
                    )
            );

            document.add(
                    new Paragraph(
                            "Account Statement"
                    )
            );

            document.add(
                    new Paragraph(
                            "Account Number : "
                                    + account.getAccountNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Customer : "
                                    + user.getFirstName()
                                    + " "
                                    + user.getLastName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Date Range : "
                                    + fromDate
                                    + " to "
                                    + toDate
                    )
            );

            document.add(
                    new Paragraph(
                            "-----------------------------------"
                    )
            );

            for (Transaction txn : transactions) {

                document.add(
                        new Paragraph(
                                txn.getTransactionDate()
                                        + " | "
                                        + txn.getTransactionType()
                                        + " | ₹"
                                        + txn.getAmount()
                        )
                );
            }

            document.close();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error generating PDF",
                    e
            );
        }

        return baos.toByteArray();
    }
}