package com.kubernetesdemo.awsuser.common.component;

import com.kubernetesdemo.awsuser.user.model.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Log4j2
@Component
public class JwtProvider {
    @Value("${jwt.iss}")
    private String issuer;
    private final SecretKey secretKey;
    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);

    public JwtProvider(@Value("${jwt.secret}") String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createToken(UserDto user) {

        String token = Jwts.builder()
                .signWith(secretKey)
                .expiration(Date.from(expiredDate))
                .subject("rod")
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("job",user.getJob())
                .compact();

        log.info("로그인 성공으로 발급된 토큰" + token);

        return token;
    }
}
