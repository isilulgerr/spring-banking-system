package com.isil.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    @NotNull(message = "Account name is required")
    private String accountName;
    @NotNull(message = "Balance is required")
    private BigDecimal balance;
    @NotNull(message = "User is required")
    private UserDTO user;
}
