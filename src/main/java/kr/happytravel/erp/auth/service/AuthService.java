package kr.happytravel.erp.auth.service;

import kr.happytravel.erp.auth.model.LoginInfoModel;

public interface AuthService {
	// 로그인
	LoginInfoModel login(String empId, String password) throws Exception;
}
