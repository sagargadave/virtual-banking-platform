package com.virtualbank.platform.repository;

import com.virtualbank.platform.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}