package com.isil.service;

import java.math.BigDecimal;
import java.util.List;
import com.isil.dto.TransactionDTO;

public interface ITransactionService {
    List<TransactionDTO> getAllTransactions();
    TransactionDTO getTransactionById(Long id);
    List<TransactionDTO> getTransactionsByAccountId(Long accountId);
    TransactionDTO deposit(Long accountId, BigDecimal amount);
    TransactionDTO withdraw(Long accountId, BigDecimal amount);
}
