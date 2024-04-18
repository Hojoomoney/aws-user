package com.kubernetesdemo.awsuser.common.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(CustomException.class)
//    ResponseEntity<ErrorCode> handleLoginFailed(CustomException e){
//        return ResponseEntity.status(ErrorCode.LOGIN_FAILED.getStatus()).body(ErrorCode.LOGIN_FAILED.getMessage());
//    }
}
