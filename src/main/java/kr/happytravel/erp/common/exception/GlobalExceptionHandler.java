package kr.happytravel.erp.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    // 디비 작업중 발생하는 에러처리
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex, WebRequest request) {
        logger.error("Database operation failed: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Database operation in Spring failed: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 쿼리 실행중 발생한 에러를 에러처리
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException ex, WebRequest request) {
        logger.error("SQL error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("SQL error in DB occurred: " + ex.getMessage());
    }
    // SQL 문법 오류 에러처리
    @ExceptionHandler(MySQLSyntaxErrorException.class)
    public ResponseEntity<String> handleMySQLSyntaxErrorException(MySQLSyntaxErrorException ex) {
        logger.error("MySQL syntax error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("MySQLSyntax Error occurred: " + ex.getLocalizedMessage());
    }

    // 객체 참조시에 Null인 경우 이 에러처리
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        logger.error("Null pointer exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Null pointer exception occurred: " + ex.getLocalizedMessage());
    }

    // 잘못된 인자가 메소드에 전달되면 실행되는 에러처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.warn("Invalid argument: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid argument: " + ex.getLocalizedMessage());
    }

    // 필수 요청 파라미터가 없을 때 발생하는 에러처리
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.warn("Missing required parameter: {}", ex.getParameterName(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Missing required parameter: " + ex.getParameterName());
    }

    // 잘못된 타입의 매개변수가 클라이언트에서 API로 전달이 되었을 때 에러처리
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.warn("Parameter type mismatch: {} requires type {}", ex.getName(), ex.getRequiredType().getSimpleName(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Parameter type mismatch: " + ex.getName() + " requires type " + ex.getRequiredType().getSimpleName());
    }

    // 권한이 없는 상황에서 접근시 에러처리
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        logger.warn("Access denied: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied: " + ex.getLocalizedMessage());
    }

    // JSON 파싱 시 에러 발생 시 에러처리
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex, WebRequest request) {
        logger.error("JSON processing error: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("JSON processing error: " + ex.getOriginalMessage());
    }

    // HTTP 요청 본문을 읽을 수 없는 형식일때 에러처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.warn("Request body is unreadable: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Request body is unreadable: " + ex.getLocalizedMessage());
    }

    // 기타 명시되지 않은 에러처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
