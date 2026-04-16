package com.isil.controller.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.ITransactionController;
import com.isil.dto.TransactionDTO;
import com.isil.service.ITransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControllerImpl implements ITransactionController {

    @Autowired
    private ITransactionService transactionService;

    @Override
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @Override
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @Override
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<TransactionDTO> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        TransactionDTO transaction = transactionService.deposit(accountId, amount);
        return ResponseEntity.ok(transaction);
    }

    @Override
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<TransactionDTO> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        TransactionDTO transaction = transactionService.withdraw(accountId, amount);
        return ResponseEntity.ok(transaction);
    }
}
