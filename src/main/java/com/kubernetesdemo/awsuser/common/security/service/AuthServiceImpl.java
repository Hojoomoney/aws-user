package com.kubernetesdemo.awsuser.common.security.service;

import com.kubernetesdemo.awsuser.common.component.Messenger;
import com.kubernetesdemo.awsuser.user.model.UserDto;


import com.kubernetesdemo.awsuser.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserRepository repository;

    @Override
    public Messenger login(UserDto dto) {

        boolean flag = repository.findByUsername(dto.getUsername()).get().getPassword().equals(dto.getPassword());

        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .accessToken(flag ? createToken(dto) : "none")
                .build();
    }

    @Override
    public String createToken(UserDto user) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenValidity = now.plusSeconds(24*60*60*1000);

        String token = Jwts.builder()
                .claims()
                .issuer("rod.com")
                .add("sub", "User auth")
                .add("exp", tokenValidity)
                .add("userId", user.getId())
                .add("username", user.getUsername())
                .add("job","admin")
                .and()
                .compact();

        log.info("로그인 성공으로 발급된 토큰" + token);

        return token;
    }
}
