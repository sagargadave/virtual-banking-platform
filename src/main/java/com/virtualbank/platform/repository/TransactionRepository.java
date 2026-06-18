package com.virtualbank.platform.repository;

import com.virtualbank.platform.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}