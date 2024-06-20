package kr.happytravel.erp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

// HandlerInterceptorAdapter 클래스를 상속받던 것을 HandlerInterceptor 인터페이스를 구현
public class AuthCheckInterceptor implements HandlerInterceptor {
	// Set logger
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 현재 세션을 가져오되, 세션이 없으면 null 반환
		HttpSession session = request.getSession(false);
		if (session != null) {

			// 요청 URI를 가져옴
			String requestUri = request.getRequestURI();
			// 클라이언트의 IP 주소 가져오기
			String clientIp = request.getRemoteAddr();

			// 루트 경로 요청을 처리
			if ("/".equals(requestUri)) {
				// 루트 경로로 들어온 것을 로그로 남김
				logger.info("root path in, client IP: {}", clientIp);
			}

			// 세션에서 "usrMnuAtrt" 속성을 가져옴
			Object authInfo = session.getAttribute("usrMnuAtrt");
			if (authInfo != null) {
				// 인증 정보가 있으면 요청을 계속 처리
				return true;
			}
		}

		// AJAX 요청인지 확인
		String ajaxCall = (String) request.getHeader("AJAX");
		if ("true".equals(ajaxCall)) {
			// AJAX 요청이면 901 에러 코드 전송
			response.sendError(901);
		}

		// 인증 정보가 없으면 요청을 계속 처리하지 않음
		return true;
	}
}
