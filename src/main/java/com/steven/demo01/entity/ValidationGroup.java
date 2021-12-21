package com.steven.demo01.entity;

public class ValidationGroup {

    /**
     * 新增分组
     */
    public interface AddGroup {};

    /**
     * 编辑分组
     */
    public interface EditGroup {};

    public interface CustomGroup {}

    /**
     * id必填分组
     */
    public interface IdGroup {};
}
