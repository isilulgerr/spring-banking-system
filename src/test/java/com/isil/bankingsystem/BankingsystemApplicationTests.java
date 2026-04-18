package com.isil.bankingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.isil.dto.TransactionDTO;
import com.isil.entity.Account;
import com.isil.entity.Transaction;
import com.isil.entity.TransactionType;
import com.isil.repository.AccountRepository;
import com.isil.repository.TransactionRepository;
import com.isil.service.ITransactionService;
import com.isil.starter.BankingsystemApplication;

@SpringBootTest(classes = BankingsystemApplication.class)
class BankingsystemApplicationTests {

	@Autowired
	private ITransactionService transactionService;

	@MockitoBean
	private AccountRepository accountRepository;

	@MockitoBean
	private TransactionRepository transactionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Deposit successful should increase balance and save transaction")
	void shouldDepositMoneySuccessfully() {
		// GIVEN
		Account account = new Account();
		account.setId(1L);
		account.setBalance(BigDecimal.valueOf(100.0));

		given(accountRepository.findById(1L)).willReturn(Optional.of(account));
		
		Transaction mockSavedTransaction = new Transaction();
		mockSavedTransaction.setId(10L);
		mockSavedTransaction.setAmount(BigDecimal.valueOf(50.0));
		mockSavedTransaction.setType(TransactionType.DEPOSIT);
		mockSavedTransaction.setReceiverAccount(account);
		
		given(transactionRepository.save(any(Transaction.class))).willReturn(mockSavedTransaction);

		// WHEN
		TransactionDTO result = transactionService.deposit(1L, BigDecimal.valueOf(50.0));

		// THEN
		assertEquals(150.0, account.getBalance().doubleValue());
		assertNotNull(result);
		assertEquals(50.0, result.getAmount().doubleValue());
		
		verify(accountRepository, times(1)).save(account);
		verify(transactionRepository, times(1)).save(any(Transaction.class));
	}

	@Test
	@DisplayName("Withdraw successful should decrease balance and save transaction")
	void shouldWithdrawMoneySuccessfully() {
		// GIVEN
		Account account = new Account();
		account.setId(1L);
		account.setBalance(BigDecimal.valueOf(200.0));

		given(accountRepository.findById(1L)).willReturn(Optional.of(account));
		
		Transaction mockSavedTransaction = new Transaction();
		mockSavedTransaction.setId(11L);
		mockSavedTransaction.setAmount(BigDecimal.valueOf(50.0));
		mockSavedTransaction.setType(TransactionType.WITHDRAWAL);
		mockSavedTransaction.setSenderAccount(account);
		
		given(transactionRepository.save(any(Transaction.class))).willReturn(mockSavedTransaction);

		// WHEN
		TransactionDTO result = transactionService.withdraw(1L, BigDecimal.valueOf(50.0));

		// THEN
		assertEquals(150.0, account.getBalance().doubleValue());
		assertNotNull(result);
		assertEquals(50.0, result.getAmount().doubleValue());
		
		verify(accountRepository, times(1)).save(account);
		verify(transactionRepository, times(1)).save(any(Transaction.class));
	}
}
