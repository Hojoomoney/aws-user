package com.kubernetesdemo.awsuser.common.security.service;

import com.kubernetesdemo.awsuser.common.component.Messenger;
import com.kubernetesdemo.awsuser.user.model.UserDto;

public interface AuthService {
    Messenger login(UserDto dto);

    String createToken(UserDto user);
}
