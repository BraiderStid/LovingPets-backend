package com.lovingpets.auth_service.web.controller;

import com.lovingpets.auth_service.domain.dto.AdminCreateUserRequest;
import com.lovingpets.auth_service.domain.dto.CustomerRegisterRequest;
import com.lovingpets.auth_service.domain.dto.UpdateUserRequest;
import com.lovingpets.auth_service.domain.dto.UserResponse;
import com.lovingpets.auth_service.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
     }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUser(@PathVariable Long id) {
        userService.disableUser(id);
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableUser(@PathVariable Long id) {
        userService.enableUser(id);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerCustomer(
            @Valid @RequestBody CustomerRegisterRequest request) {

        UserResponse response = userService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/admin/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUserByAdmin(
            @RequestBody @Valid AdminCreateUserRequest request) {

        return userService.createUserByAdmin(request);
    }

    @PatchMapping("/me/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequest request) {

        UserResponse updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }


}
