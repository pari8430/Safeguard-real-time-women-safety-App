package com.safeguard.controller;

import com.safeguard.model.User;
import com.safeguard.security.CustomUserDetails;
import com.safeguard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(401).body("Invalid token or not logged in");
        }

        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(user);
    }
}
