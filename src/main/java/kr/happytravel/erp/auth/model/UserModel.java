package kr.happytravel.erp.auth.model;

import lombok.Data;

@Data
public class UserModel {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
