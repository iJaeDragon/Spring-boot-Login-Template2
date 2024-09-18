package com.billyAndMillie.game.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        // 로그인 실패 시 에러 메시지를 파라미터로 전달하여 리다이렉트
        //response.sendRedirect("/login/loginView?error=true&exception=" + exception.getMessage());

        response.sendRedirect("/login/loginView?fail");
    }
}
