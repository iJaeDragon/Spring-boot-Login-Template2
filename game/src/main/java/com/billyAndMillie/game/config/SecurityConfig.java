package com.billyAndMillie.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler customLoginSuccessHandler;

    public SecurityConfig(CustomLoginSuccessHandler customLoginSuccessHandler) {
        this.customLoginSuccessHandler = customLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .requestMatchers("/", "/main", "/login/**").permitAll() // 비로그인 접근 허용 경로
                    .requestMatchers("/login/myPageView").authenticated()  // 비로그인 접근 불가
                    .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
                    .and()
                .formLogin()
                    .loginPage("/login/loginView") // 로그인 페이지 설정
                    .loginProcessingUrl("/login/login") // 로그인 인증 프로세싱 url
                    .usernameParameter("userId") // 사용자 ID 필드 이름 설정
                    .passwordParameter("userPw") // 비밀번호 필드 이름 설정
                    .failureUrl("/login?error=true")  // 로그인 실패 시 에러 메시지 전달
                    .successHandler(customLoginSuccessHandler)  // 커스텀 성공 핸들러 등록
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .sessionManagement()
                    .maximumSessions(1) // 동시에 허용할 세션 수 설정 (1로 설정하여 중복 로그인 비활성화)
                    .expiredUrl("/main?expired") // 세션이 만료된 경우 이동할 URL 설정
                    .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); // 세션 정책 설정

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }

}