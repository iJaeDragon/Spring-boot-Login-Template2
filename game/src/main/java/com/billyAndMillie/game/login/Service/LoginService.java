package com.billyAndMillie.game.login.Service;

import com.billyAndMillie.game.common.dao.CommonDao;
import com.billyAndMillie.game.login.domain.User;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Resource(name="commonDao")
    private CommonDao commonDao;

    public User selectByUserNo(User user) {

        return (User) commonDao.selectOne("mappers.login.selectByUserNo", user);
    }

    public boolean userRegister(User user) throws RuntimeException {
        boolean isSuccess = true;

        if(user.getUserId() == null || "".equals(user.getUserId())) {
            throw new RuntimeException("아이디가 입력되지 않았습니다.");
        } else if(user.getUserNm() == null || "".equals(user.getUserNm())) {
            throw new RuntimeException("이름이 입력되지 않았습니다.");
        } else if(user.getUserPw() == null || "".equals(user.getUserPw())) {
            throw new RuntimeException("비밀번호가 입력되지 않았습니다.");
        }

        int idCount = commonDao.selectOne("mappers.login.selectIdCount", user);

        if(idCount != 0) {
            throw new RuntimeException("중복된 아이디 입니다.");
        }

        user.setUserPw(new BCryptPasswordEncoder().encode(user.getUserPw()));

        commonDao.insert("mappers.login.insertUser", user);

        return isSuccess;
    }

}
