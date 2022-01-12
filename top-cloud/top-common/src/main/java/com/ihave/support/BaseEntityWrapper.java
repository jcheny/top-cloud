package com.ihave.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/20 下午5:42
 */
public abstract class BaseEntityWrapper<E, V> {

    /**
     * 单个实体类包装
     *
     * @param entity 实体类
     * @return V
     */
    public abstract V entityVO(E entity);

    /**
     * 实体类集合包装
     *
     * @param list 猎豹
     * @return List V
     */
    public List<V> listVO(List<E> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    /**
     * 分页实体类集合包装
     *
     * @param pages 分页
     * @return Page V
     */
    public IPage<V> pageVO(IPage<E> pages) {
        List<V> records = listVO(pages.getRecords());
        IPage<V> pageVo = new Page<>(pages.getCurrent(), pages.getSize(), pages.getTotal());
        pageVo.setRecords(records);
        return pageVo;
    }



}