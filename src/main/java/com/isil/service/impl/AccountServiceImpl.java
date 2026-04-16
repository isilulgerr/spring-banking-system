package com.isil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isil.dto.AccountDTO;
import com.isil.dto.UserDTO;
import com.isil.entity.Account;
import com.isil.entity.User;
import com.isil.exception.ResourceNotFoundException;
import com.isil.repository.AccountRepository;
import com.isil.repository.UserRepository;
import com.isil.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return toDTO(account);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = toEntity(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return toDTO(savedAccount);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

        existingAccount.setAccountName(accountDTO.getAccountName());
        existingAccount.setBalance(accountDTO.getBalance());

        // Update user if provided
        if (accountDTO.getUser() != null) {
            User user = userRepository.findById(accountDTO.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found with id: " + accountDTO.getUser().getId()));
            existingAccount.setUser(user);
        }

        Account updatedAccount = accountRepository.save(existingAccount);
        return toDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        accountRepository.delete(account);
    }

    // Helper methods for conversion
    private AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountName(account.getAccountName());
        dto.setBalance(account.getBalance());

        if (account.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(account.getUser().getId());
            userDTO.setUsername(account.getUser().getUsername());
            userDTO.setEmail(account.getUser().getEmail());
            dto.setUser(userDTO);
        }

        return dto;
    }

    private Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setAccountName(dto.getAccountName());
        account.setBalance(dto.getBalance());

        if (dto.getUser() != null) {
            User user = userRepository.findById(dto.getUser().getId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User not found with id: " + dto.getUser().getId()));
            account.setUser(user);
        }

        return account;
    }
}
