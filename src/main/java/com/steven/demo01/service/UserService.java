package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.entity.SysUserRole;
import com.steven.demo01.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User register(User user);

    String login(User user);

    IPage<User> selectUserList(User user);

    void edit(User user);

    void insertUserAuth(Long userId, Long[] roleIds);

    User queryUserByUserId(Long userId);

    /**
     * 根据userid 获取用户的角色
     * @param userId
     * @return
     */
    List<SysUserRole> queryRoleIdsByUserId(Long userId);
}
