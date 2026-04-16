package com.isil.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.isil.dto.TransactionDTO;

public interface ITransactionController {
    ResponseEntity<List<TransactionDTO>> getAllTransactions();

    ResponseEntity<TransactionDTO> getTransactionById(Long id);

    ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(Long accountId);

    ResponseEntity<TransactionDTO> deposit(Long accountId, BigDecimal amount);

    ResponseEntity<TransactionDTO> withdraw(Long accountId, BigDecimal amount);
}
