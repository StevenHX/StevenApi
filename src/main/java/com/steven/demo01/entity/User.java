package com.steven.demo01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class User {
    @TableId
    @NotNull(groups = {
            ValidationGroup.EditGroup.class,
            ValidationGroup.IdGroup.class
    })
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.CustomGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String username;

    @NotBlank(message = "密码不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.CustomGroup.class,
            ValidationGroup.EditGroup.class
    })
    @Size(min = 6, max = 20, message = "密码不能小于6位,大于20位", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.CustomGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String password;

    @NotBlank(message = "手机号不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String phone;

    private String user_img;
}
