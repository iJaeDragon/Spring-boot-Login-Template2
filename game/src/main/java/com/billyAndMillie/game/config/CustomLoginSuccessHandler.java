package com.billyAndMillie.game.config;

import com.billyAndMillie.game.login.Service.LoginService;
import com.billyAndMillie.game.login.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoginService loginService;

    private final SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();

    // SavedRequestAwareAuthenticationSuccessHandler 설정
    // 로그인 성공 시 기존에 요청한 페이지로 이동, 기본적으로 /main으로 이동함.
    public CustomLoginSuccessHandler() {
        // 기본 리다이렉트 URL 설정
        successHandler.setDefaultTargetUrl("/main"); // 기본적으로 /main으로 리다이렉트
        successHandler.setAlwaysUseDefaultTargetUrl(false);  // 이전 요청이 있을 경우 해당 URL로 이동
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 로그인한 사용자의 정보를 가져옴
        String userNo = authentication.getName();

        User user = new User();
        user.setUserNo(userNo);

        user = (User) loginService.selectByUserNo(user);

        // 세션에 사용자 정보를 저장
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", user);

        // SavedRequestAwareAuthenticationSuccessHandler를 이용하여 이전 페이지로 리다이렉트
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

}