package com.steven.demo01.domain.model;

import lombok.Data;

import java.util.Set;

@Data
public class LoginInfo {
    private LoginUser loginUser;
    private Set<String> roles;
    private Set<String> permissions;
}
