package kr.happytravel.erp.auth.controller;

import kr.happytravel.erp.auth.model.UserModel;
import kr.happytravel.erp.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel loginRequest) {
        UserModel user = userService.getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            logger.warn("User not found: {}", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (userService.checkPassword(loginRequest.getPassword(), user.getPassword())) {
            logger.info("User {} logged in successfully", loginRequest.getUsername());
            return ResponseEntity.ok(user);
        } else {
            logger.warn("Invalid login attempt for user: {}", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        logger.info("User logged out successfully");
        return ResponseEntity.ok("Logout successful");
    }
}
