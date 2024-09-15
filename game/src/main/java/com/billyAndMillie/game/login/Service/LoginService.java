package com.billyAndMillie.game.login.Service;

import com.billyAndMillie.game.common.dao.CommonDao;
import com.billyAndMillie.game.login.domain.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Resource(name="commonDao")
    private CommonDao commonDao;

    public User selectByUserNo(User user) {

        return (User) commonDao.selectOne("mappers.login.selectByUserNo", user);
    }

}
