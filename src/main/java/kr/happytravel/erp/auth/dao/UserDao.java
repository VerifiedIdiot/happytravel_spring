package kr.happytravel.erp.auth.dao;

import kr.happytravel.erp.auth.model.UserModel;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    UserModel getUserByUsername(@Param("username") String username);
    void insertUser(UserModel user);
}
