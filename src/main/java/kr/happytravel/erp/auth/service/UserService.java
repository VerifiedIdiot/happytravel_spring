package kr.happytravel.erp.auth.service;

import kr.happytravel.erp.auth.model.UserModel;

public interface UserService {
    UserModel getUserByUsername(String username);
    boolean checkPassword(String rawPassword, String encodedPassword);
    void registerUser(UserModel user);
}
