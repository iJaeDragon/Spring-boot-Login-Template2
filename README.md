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
