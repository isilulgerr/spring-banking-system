package com.isil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotNull(message = "User ID is required")
    private Long id;
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Email is required")
    private String email;
}
