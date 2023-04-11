package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.entity.SysDictData;
import com.steven.demo01.domain.entity.SysDictType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典服务
 */
@Service
public interface DictService {
    /**
     * 查询字典类型列表
     */
    IPage<SysDictType> pageListType(SysDictType dictType);

    /**
     * 查询字典类型详情
     * @param dictId
     * @return
     */
    SysDictType queryDictTypeById(Long dictId);

    /**
     * 添加字典类型
     * @param dictType
     */
    void addDictType(SysDictType dictType);

    /**
     * 修改字典类型
     * @param dictType
     */
    void editDictType(SysDictType dictType);

    /**
     * 删除字典类型
     */
    void delDitType(Long dictId);

    /**
     * 查询字典数据列表
     * @param sysDictData
     * @return
     */
    IPage<SysDictData> pageListData(SysDictData sysDictData);

    /**
     * 查询字典数据详情
     * @param dictDataId
     * @return
     */
    SysDictData queryDictDataById(Long dictDataId);

    /**
     * 根据字典类型 查询字典数据数组
     */
    List<SysDictData> queryDictByDictType(String dictType);

    /**
     * 修改字典数据
     * @param dictData
     */
    void addDictData(SysDictData dictData);

    /**
     * 修改字典数据
     * @param dictData
     */
    void editDictData(SysDictData dictData);

    /**
     * 删除字典数据
     */
    void delDictData(Long dictDataId);
}
