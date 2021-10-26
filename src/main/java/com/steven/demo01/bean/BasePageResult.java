package com.steven.demo01.bean;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class BasePageResult <T> {
    private List<T> data;
    private Long totalNum;

    public BasePageResult(List<T> data, Long totalNum) {
        this.data = data;
        this.totalNum = totalNum;
    }

    public static <E> BasePageResult<E> newInstance(IPage<E> page) {
        // TODO: 2021/10/26 总数不对
        return new BasePageResult<E>(page.getRecords(), page.getTotal());
    }

}