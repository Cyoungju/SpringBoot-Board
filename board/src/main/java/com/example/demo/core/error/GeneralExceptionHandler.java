package com.example.demo.core.error;

import com.example.demo.core.error.exception.*;
import com.example.demo.core.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ExceptionHandler는 특정 컨트롤러 내에서 발생하는 예외를 처리할 때 사용하며,
// @ControllerAdvice는 전체 애플리케이션에서 발생하는 예외를 처리할 때 사용합니다.
@ControllerAdvice
public class GeneralExceptionHandler {

    // ** 데이터 유효성 검사 실패
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ** 인증 되지 않음.
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ** 권한 없음. (인증)
    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ** 권한 없음. (삭제된 게시물에 대한 접근, 로그인되지 읺은 상태에서의 접근 등등...)
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ** 서버 문제
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ** 위 예외를 제외한 모든 예외처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e) {
        ApiUtils.ApiResult<?> apiResult = ApiUtils.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}















