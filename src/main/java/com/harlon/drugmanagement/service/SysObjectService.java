package com.harlon.drugmanagement.service;

import com.harlon.drugmanagement.entity.SysObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysObject)表服务接口
 *
 * @author makejava
 * @since 2022-03-01 09:22:38
 */
public interface SysObjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysObject queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysObject 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysObject> queryByPage(SysObject sysObject, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysObject 实例对象
     * @return 实例对象
     */
    SysObject insert(SysObject sysObject);

    /**
     * 修改数据
     *
     * @param sysObject 实例对象
     * @return 实例对象
     */
    SysObject update(SysObject sysObject);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
