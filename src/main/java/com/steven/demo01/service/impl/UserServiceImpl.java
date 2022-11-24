package com.steven.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.constant.Constants;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.domain.model.LoginUser;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.UserMapper;
import com.steven.demo01.service.UserService;
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
        User oldUser = userMapper.findByUsername(user.getUserName());
        if (oldUser != null) {
            throw new CustomException(CommonResult.error("用户已存在", ""));
        }
        user.setCreateTime(new Date());
        userMapper.insert(user);
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
            redisUtil.set(token, loginUser, Constants.EXPIRE_TIME);
            return token;
        }

        throw new CustomException(CommonResult.error("登录失败", ""));
    }

    @Override
    public IPage<User> selectUserList(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(user.getUserName())) {
            queryWrapper.like("user_name", user.getUserName());
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            queryWrapper.like("email", user.getEmail());
        }
        if (StringUtils.isNotBlank(user.getPhonenumber())) {
            queryWrapper.like("phonenumber", user.getPhonenumber());
        }
        if (StringUtils.isNotBlank(user.getSex())) {
            queryWrapper.eq("sex", user.getSex());
        }
        if (StringUtils.isNotBlank(user.getStatus())) {
            queryWrapper.eq("status", user.getStatus());
        }
        IPage<User> pages = new Page<>(user.getPageNum(), user.getPageSize());
        return userMapper.selectPage(pages, queryWrapper);
    }

    @Override
    public void edit(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", user.getUserId());
        user.setUpdateTime(new Date());
        int rows = userMapper.update(user, updateWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("修改失败", ""));
    }


}
