package com.isil.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    @NotNull(message = "Transaction ID is required")
    private Long id;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Transaction time is required")
    private LocalDateTime transactionTime;
    @NotNull(message = "Sender account is required")
    private AccountDTO senderAccount;
    @NotNull(message = "Receiver account is required")
    private AccountDTO receiverAccount;
    @NotNull(message = "Description is required")
    private String description;
}
