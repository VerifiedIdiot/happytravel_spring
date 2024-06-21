package kr.happytravel.erp.auth.dao;

import org.apache.ibatis.annotations.Param;

import kr.happytravel.erp.auth.model.LoginInfoModel;

public interface AuthDao {

	LoginInfoModel login(@Param("empId") String empId, @Param("password") String password) throws Exception;
}
