package com.isil.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.IAccountController;
import com.isil.dto.AccountDTO;
import com.isil.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountControllerImpl implements IAccountController {

    @Autowired
    private IAccountService accountService;

    @Operation(summary = "Get all accounts", description = "Returns a list of all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Accounts not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Accounts not found\"}")))
    })
    @Override
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Get account by ID", description = "Returns an account by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Account not found\"}")))
    })
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Create account", description = "Creates a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created account", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "400", description = "Invalid account data", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid account data\"}")))
    })
    @Override
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO createdAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @Operation(summary = "Update account", description = "Updates an account by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated account", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Account not found\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid account data", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid account data\"}")))
    })
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    @Operation(summary = "Delete account", description = "Deletes an account by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted account"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Account not found\"")))
    })
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
