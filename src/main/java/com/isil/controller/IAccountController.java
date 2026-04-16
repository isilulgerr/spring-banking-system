package com.isil.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.isil.dto.AccountDTO;

public interface IAccountController {
    ResponseEntity<List<AccountDTO>> getAllAccounts();

    ResponseEntity<AccountDTO> getAccountById(Long id);

    ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO);

    ResponseEntity<AccountDTO> updateAccount(Long id, AccountDTO accountDTO);

    ResponseEntity<Void> deleteAccount(Long id);
}
