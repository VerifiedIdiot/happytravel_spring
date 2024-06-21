package kr.happytravel.erp.auth.service;

import org.springframework.stereotype.Service;

import kr.happytravel.erp.auth.dao.AuthDao;
import kr.happytravel.erp.auth.model.LoginInfoModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final AuthDao authDao;

	@Override
	public LoginInfoModel login(String empId, String password) throws Exception {
		return authDao.login(empId, password);
	}

}
