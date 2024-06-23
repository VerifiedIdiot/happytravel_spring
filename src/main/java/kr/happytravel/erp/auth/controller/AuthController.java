package kr.happytravel.erp.auth.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.happytravel.erp.auth.model.LoginInfoModel;
import kr.happytravel.erp.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginInfoModel> login(@RequestBody Map<String, String> paramMap) throws Exception {
		String empId = paramMap.get("empId");
		String password = paramMap.get("password");
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for login data employee ID: {}, PASSWORD: {}", empId, password);

			return ResponseEntity.ok(authService.login(empId, password));
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching login data: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
