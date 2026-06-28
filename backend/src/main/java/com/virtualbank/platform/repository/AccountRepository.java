package com.virtualbank.platform.repository;

import com.virtualbank.platform.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.virtualbank.platform.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByUser(User user);
}