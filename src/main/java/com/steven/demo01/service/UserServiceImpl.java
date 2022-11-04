package com.steven.demo01.service;

import com.steven.demo01.constant.CacheConstants;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.domain.model.LoginUser;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.UserMapper;
import com.steven.demo01.utils.JwtUtils;
import com.steven.demo01.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void register(User user) {
//        User oldUser = userMapper.findByUsername(user.getUsername());
//        if (oldUser != null) {
//            throw new CustomException(CommonResult.error("用户已存在", ""));
//        }
//        userMapper.insert(user);
    }

    @Override
    public String login(User user) {
        User sysUser = userMapper.findByUsername(user.getUserName());
        if (sysUser == null) {
            throw new CustomException(CommonResult.error("用户未注册", ""));
        }
        if (
                StringUtils.equals(user.getUserName(), sysUser.getUserName()) &&
                StringUtils.equals(user.getPassword(), sysUser.getPassword())
        ) {
            String token = JwtUtils.makeToken(sysUser.getUserId());
            // 缓存登录信息
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(sysUser);
            loginUser.setLoginTime(new Date().getTime());
            redisUtil.set(token, loginUser, 60L * 60L);
            return token;
        }

        throw new CustomException(CommonResult.error("登录失败", ""));
    }
}
