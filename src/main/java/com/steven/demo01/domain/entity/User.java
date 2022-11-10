package com.steven.demo01.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.steven.demo01.domain.BaseSearchDto;
import com.steven.demo01.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


@Data
public class User extends BaseSearchDto implements Serializable {

    /**
     * 用户ID
     */
    @TableId
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户名不能为空", groups = {
            ValidationGroup.CustomGroup.class,
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String userName;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户名不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String nickName;

    /**
     * 用户邮箱
     */
    @Pattern(message = "邮箱不符合要求", regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String email;

    /**
     * 手机号码
     */
    @Pattern(message = "手机号不符合要求", regexp = "(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String phonenumber;

    /**
     * 用户性别
     */
    @NotBlank(message = "性别不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    @Pattern(message = "密码不符合要求", regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[~!@#$%^&*()_]).{8,30}", groups = {
            ValidationGroup.CustomGroup.class,
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
