package com.isil.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private String type;
    private LocalDateTime transactionTime;
    private AccountDTO senderAccount;
    private AccountDTO receiverAccount;
    private String description;
}
