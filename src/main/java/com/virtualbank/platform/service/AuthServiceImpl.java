package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.RegisterRequestDto;
import com.virtualbank.platform.entity.User;
import com.virtualbank.platform.enums.Role;
import com.virtualbank.platform.repository.AccountRepository;
import com.virtualbank.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.virtualbank.platform.entity.Account;
import com.virtualbank.platform.enums.AccountType;
import com.virtualbank.platform.enums.AccountStatus;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

        Account account = Account.builder()
                .accountNumber("VBP" + savedUser.getId())
                .accountType(AccountType.SAVINGS)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .user(savedUser)
                .build();

        accountRepository.save(account);

        return "User Registered Successfully";
    }

}