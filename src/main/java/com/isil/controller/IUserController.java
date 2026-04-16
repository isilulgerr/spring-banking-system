package com.isil.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.isil.dto.UserDTO;

public interface IUserController {
    ResponseEntity<List<UserDTO>> getAllUsers();

    ResponseEntity<UserDTO> getUserById(Long id);

    ResponseEntity<UserDTO> createUser(UserDTO userDTO);

    ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDTO);

    ResponseEntity<Void> deleteUser(Long id);
}
