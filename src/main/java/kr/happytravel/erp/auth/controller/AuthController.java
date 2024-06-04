package kr.happytravel.erp.auth.controller;

import kr.happytravel.erp.auth.model.UserModel;
import kr.happytravel.erp.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        UserModel user = userService.getUserByUsername(username);
        if (user == null) {
            return "User not found";
        }

        if (userService.checkPassword(password, user.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            logger.info("User {} logged in successfully", username);
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            logger.info("User logged out successfully");
        }
        return "Logout successful";
    }
}
