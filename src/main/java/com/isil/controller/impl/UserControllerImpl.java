package com.isil.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.IUserController;
import com.isil.dto.UserDTO;
import com.isil.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"John Doe\", \"email\": \"[EMAIL_ADDRESS]\"}"))),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Users not found\"}")))
    })
    @Override
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user by ID", description = "Returns a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"John Doe\", \"email\": \"[EMAIL_ADDRESS]\"}"))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"User not found\"}")))
    })
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Create a new user", description = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created user", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"John Doe\", \"email\": \"[EMAIL_ADDRESS]\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid user data", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid user data\"}")))
    })
    @Override
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Update a user", description = "Updates an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"John Doe\", \"email\": \"[EMAIL_ADDRESS]\"}"))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"User not found\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid user data", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Invalid user data\"}")))
    })
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a user", description = "Deletes an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted user", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"User deleted successfully\"}"))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"User not found\"}")))
    })

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
