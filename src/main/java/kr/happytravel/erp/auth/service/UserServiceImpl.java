package kr.happytravel.erp.auth.service;

import kr.happytravel.erp.auth.dao.UserDao;
import kr.happytravel.erp.auth.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserModel getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public void registerUser(UserModel user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.insertUser(user);
            logger.info("User {} registered successfully", user.getUsername());
        } catch (Exception e) {
            logger.error("Error registering user: {}", user.getUsername(), e);
            throw new RuntimeException("User registration failed");
        }
    }
}
