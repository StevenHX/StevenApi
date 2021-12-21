package com.steven.demo01.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class BasePageResult <T> {
    private List<T> list;
    private Long totalNum;

    public BasePageResult(List<T> data, Long totalNum) {
        this.list = data;
        this.totalNum = totalNum;
    }

    public static <E> BasePageResult<E> newInstance(IPage<E> page) {
        return new BasePageResult<E>(page.getRecords(), page.getTotal());
    }

}