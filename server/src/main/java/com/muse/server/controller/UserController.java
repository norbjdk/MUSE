package com.muse.server.controller;

import com.muse.server.model.dto.UserResponse;
import com.muse.server.model.repo.UserRepo;
import com.muse.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse response = userService.getUserByUsername(username);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        UserResponse response = userService.getUserByUsername(username);

        return ResponseEntity.ok(response);
    }
}
