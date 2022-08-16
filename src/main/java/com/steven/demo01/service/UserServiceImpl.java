package com.steven.demo01.service;

import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.entity.User;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.UserMapper;
import com.steven.demo01.utils.JwtUtils;
import com.steven.demo01.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void register(User user) {
        User oldUser = userMapper.findByUsername(user.getUsername());
        if (oldUser != null) {
            throw new CustomException(CommonResult.error("用户已存在", ""));
        }
        userMapper.insert(user);
    }

    @Override
    public String login(User user) {
        User oldUser = userMapper.findByUsername(user.getUsername());
        if (oldUser == null) {
            throw new CustomException(CommonResult.error("用户未注册", ""));
        }
        if (StringUtils.equals(user.getUsername(), oldUser.getUsername()) && StringUtils.equals(user.getPassword(), oldUser.getPassword())) {
            String token = JwtUtils.makeToken(oldUser.getId());
            redisUtil.set(oldUser.getId().toString(), token,8L * 60L * 60L);
            return token;
        }
        throw new CustomException(CommonResult.error("登录失败", ""));
    }
}
