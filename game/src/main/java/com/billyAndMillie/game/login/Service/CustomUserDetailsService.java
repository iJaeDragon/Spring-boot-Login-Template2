package com.billyAndMillie.game.login.Service;

import com.billyAndMillie.game.common.dao.CommonDao;
import com.billyAndMillie.game.login.domain.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource(name="commonDao")
    private CommonDao commonDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = (User) commonDao.selectOne("mappers.login.selectByUserId", userId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserNo(), user.getUserPw(), new ArrayList<>());
    }
}
