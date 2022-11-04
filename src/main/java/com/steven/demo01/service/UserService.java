package com.steven.demo01.service;

import com.steven.demo01.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(User user);
    String login(User user);
}
