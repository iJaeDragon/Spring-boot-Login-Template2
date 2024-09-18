# Spring-boot-Login-Template2

## Project

* Lenguage : Java
* Type : Gradle - Groovy
* JDK : 17.0.9
* Java : 17
* Package Create : Jae
* Spring boot : 3.3.3
* Template Engines : thymeleaf

## DataBase

* DBMS : PostgreSQL
* Version : 13

### Script

```
  create table tb_user_info
  (
      user_no varchar(10)  primary key,
      user_id varchar(25)  unique not null,
      user_pw varchar(250) not null,
      user_nm varchar(50),
      user_role numeric(2) not null default 2
  );
  
  comment on table tb_user_info is '유저 정보 테이블';
  
  comment on column tb_user_info.user_no is '사용자 번호';
  comment on column tb_user_info.user_id is '사용자 아이디';
  comment on column tb_user_info.user_pw is '사용자 비밀번호';
  comment on column tb_user_info.user_nm is '사용자 이름';
  comment on column tb_user_info.user_role is '사용자 역할';
```

## File Description

```
    ─src
      ├─main
      │  ├─java
      │  │  └─com
      │  │      └─billyAndMillie
      │  │          └─game
      │  │              ├─common
      │  │              │  ├─dao
      │  │              │  └─domain
      │  │              ├─config
      │  │              ├  ├─CustomAuthenticationEntryPoint.java # POST 요청과 GET 요청을 구분하고 처리하기 위한 설정
      │  │              ├  ├─CustomAuthenticationFailureHandler.java # 로그인 실패 시 에러 전달 설정
      │  │              ├  ├─CustomLoginSuccessHandler.java # 로그인 성공 시 처리 설정
      │  │              │  └─SecurityConfig.hava # 시큐리티 관련 설정 세팅
      │  │              ├─login
      │  │              │  ├─Controller
      │  │              │  ├─domain
      │  │              │  └─Service
      │  │              └─main
      │  │                  └─Controller
      │  └─resources
      │      ├─mapper
      │      │  └─login
      │      ├─static
      │      │  ├─css
      │      │  └─js
      │      └─templates
      │      │   ├─common
      │      │   │  ├─fragments
      │      │   │  │ ├─config.html # 공통설정 정의
      │      │   │  │ └─header.html # 공통헤더 영역
      │      │   │  └─layouts
      │      │   │  │ ├─defaultLayout.html # 기본 공통 레이아웃
      │      │   │  │ └─utilLayout.html # 공통설정을 불러오는 공통 레이아웃
      │      │   └─pages
      │      │       ├─login
      │      │       └─main
      │      └─application.yml # 서버 세팅 관련 설정
      └─test
          └─java
              └─com
                  └─billyAndMillie
                      └─game
```

## application.yml

```
  spring:
    application:
      name: game
    datasource:
      url: # 수정 필요
      username: # 수정 필요
      password: # 수정 필요
      driver-class-name: org.postgresql.Driver
    thymeleaf:
      cache: false
      check-template-location: true
      prefix: classpath:/templates/
      suffix: .html
      enabled: true
  
  # MyBatis Configuration
  # Locations of MyBatis mapper XML files
  mybatis:
      mapper-locations: classpath:mapper/**/*.xml
      configuration:
        map-underscore-to-camel-case: true # mybatis ??? domain? ???? ? ??? ex) (SQL)USER_NO -> (Domain)userNo
  
  # Server Info
  server:
    port: 80
```
