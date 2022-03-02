package com.harlon.drugmanagement.service;

import com.harlon.drugmanagement.entity.SysObjectKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysObjectKind)表服务接口
 *
 * @author makejava
 * @since 2022-03-01 09:21:20
 */
public interface SysObjectKindService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysObjectKind queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysObjectKind 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysObjectKind> queryByPage(SysObjectKind sysObjectKind, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysObjectKind 实例对象
     * @return 实例对象
     */
    SysObjectKind insert(SysObjectKind sysObjectKind);

    /**
     * 修改数据
     *
     * @param sysObjectKind 实例对象
     * @return 实例对象
     */
    SysObjectKind update(SysObjectKind sysObjectKind);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
