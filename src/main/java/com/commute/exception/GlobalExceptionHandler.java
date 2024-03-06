package com.commute.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 등록 안된 id 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandle(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body("error : " + exception.getMessage());
    }

    // 비즈니스 로직 관련 예외 처리 (예시: 이미 출근 처리됨, 퇴근 처리 안 됨)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> illegalStateExceptionHandle(IllegalStateException exception) {
        return ResponseEntity.badRequest().body("error: " + exception.getMessage());
    }

    // 날짜 포맷 관련 예외 처리
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> dateTimeParseExceptionHandle(DateTimeParseException exception) {
        return ResponseEntity.badRequest().body("error: 날짜 형식은 YYYY-MM 형식만 가능합니다. (예시 : 1999-01)");
    }
}
