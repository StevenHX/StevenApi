package com.steven.demo01.service;

import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.entity.User;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.UserMapper;
import com.steven.demo01.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) {
        User oldUser = userMapper.findByUsername(user.getUsername());
        if (oldUser != null) {
            throw new CustomException(CommonResult.error("用户已存在",""));
        }
        userMapper.insert(user);
    }

    @Override
    public String login(User user) {
        User oldUser = userMapper.findByUsername(user.getUsername());
        if (oldUser == null) {
            throw new CustomException(CommonResult.error("用户未注册",""));
        }
        return JwtUtils.makeToken((long) oldUser.getId());
    }
}
