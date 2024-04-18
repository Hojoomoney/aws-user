package com.kubernetesdemo.awsuser.common.exception;

import com.kubernetesdemo.awsuser.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}
