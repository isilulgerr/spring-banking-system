package com.isil.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isil.dto.AccountDTO;
import com.isil.dto.TransactionDTO;
import com.isil.entity.Account;
import com.isil.entity.Transaction;
import com.isil.entity.TransactionType;
import com.isil.exception.InsufficientFundsException;
import com.isil.exception.ResourceNotFoundException;
import com.isil.repository.AccountRepository;
import com.isil.repository.TransactionRepository;
import com.isil.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDTO deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setReceiverAccount(account);
        transaction.setDescription("Deposit");

        Transaction savedTransaction = transactionRepository.save(transaction);
        return toDTO(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionDTO withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal. Available: " + account.getBalance());
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setSenderAccount(account);
        transaction.setDescription("Withdrawal");

        Transaction savedTransaction = transactionRepository.save(transaction);
        return toDTO(savedTransaction);
    }

    // Helper methods for conversion
    private TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType() != null ? transaction.getType().name() : null);
        dto.setTransactionTime(transaction.getTransactionTime());
        dto.setDescription(transaction.getDescription());

        if (transaction.getSenderAccount() != null) {
            AccountDTO senderDTO = new AccountDTO();
            senderDTO.setId(transaction.getSenderAccount().getId());
            senderDTO.setAccountName(transaction.getSenderAccount().getAccountName());
            senderDTO.setBalance(transaction.getSenderAccount().getBalance());
            dto.setSenderAccount(senderDTO);
        }

        if (transaction.getReceiverAccount() != null) {
            AccountDTO receiverDTO = new AccountDTO();
            receiverDTO.setId(transaction.getReceiverAccount().getId());
            receiverDTO.setAccountName(transaction.getReceiverAccount().getAccountName());
            receiverDTO.setBalance(transaction.getReceiverAccount().getBalance());
            dto.setReceiverAccount(receiverDTO);
        }

        return dto;
    }
}
