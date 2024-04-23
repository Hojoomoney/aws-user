package com.kubernetesdemo.awsuser.common.component.interceptor;

import com.kubernetesdemo.awsuser.common.component.security.JwtProvider;
import com.kubernetesdemo.awsuser.user.model.User;
import com.kubernetesdemo.awsuser.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final UserRepository repository;
    @Override //request
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String token = jwtProvider.extractTokenFromHeader(request);
//        log.info("1 - 인터셉터 토큰 로그 Bearer 포함 : {}", token);
//
//        if(token.equals("undefined")){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        Long id = jwtProvider.getPayload(token).get("id",Long.class);
//
//        log.info("2 - 인터셉터 사용자 Id : {}", id);
//
//        Optional<User> user = repository.findById(id);
//
//        log.info("3 - 인터셉터 사용자 정보 : {}", user);
//
//        if(!user.isPresent()){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//        log.info("4 - 최종 여부 : {}", true);

        boolean s = Stream.of(request)
                .map(jwtProvider::extractTokenFromHeader)
                .filter(token -> !token.equals("undefined"))
                .peek(token -> log.info("1 - 인터셉터 토큰 로그 Bearer 포함 : {}",token))
                .map(token -> jwtProvider.getPayload(token).get("id", Long.class))
                .peek(id -> log.info("2 - 인터셉터 사용자 Id : {}", id))
                .anyMatch(repository::existsById);

        return s;
    }

    @Override //response
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //exception
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}