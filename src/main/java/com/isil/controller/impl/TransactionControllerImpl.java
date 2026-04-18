package com.isil.controller.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.ITransactionController;
import com.isil.dto.TransactionDTO;
import com.isil.service.ITransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControllerImpl implements ITransactionController {

    @Autowired
    private ITransactionService transactionService;

    @Operation(summary = "Get all transactions", description = "Returns a list of all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Transactions not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Transactions not found\"}")))
    })
    @Override
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Get transaction by ID", description = "Returns a transaction by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Transaction not found\"}")))
    })
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @Operation(summary = "Get transactions by account ID", description = "Returns a list of transactions by account ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Transactions not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Transactions not found\"}")))
    })
    @Override
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Deposit amount to account", description = "Deposits an amount to an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deposited amount", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Account not found\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid amount", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid amount\"}")))
    })
    @Override
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<TransactionDTO> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        TransactionDTO transaction = transactionService.deposit(accountId, amount);
        return ResponseEntity.ok(transaction);
    }

    @Operation(summary = "Withdraw amount from account", description = "Withdraws an amount from an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully withdrew amount", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"accountName\": \"My Account\", \"balance\": 1000.0}"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Account not found\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid amount", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid amount\"}")))
    })
    @Override
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<TransactionDTO> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        TransactionDTO transaction = transactionService.withdraw(accountId, amount);
        return ResponseEntity.ok(transaction);
    }
}
