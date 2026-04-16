package com.isil.service;

import java.util.List;

import com.isil.dto.AccountDTO;

public interface IAccountService {
    List<AccountDTO> getAllAccounts();

    AccountDTO getAccountById(Long id);

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO updateAccount(Long id, AccountDTO accountDTO);

    void deleteAccount(Long id);
}
