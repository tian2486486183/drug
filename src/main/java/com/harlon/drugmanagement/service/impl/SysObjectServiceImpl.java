package com.harlon.drugmanagement.service.impl;

import com.harlon.drugmanagement.entity.SysObject;
import com.harlon.drugmanagement.dao.SysObjectDao;
import com.harlon.drugmanagement.service.SysObjectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysObject)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 09:22:38
 */
@Service("sysObjectService")
public class SysObjectServiceImpl implements SysObjectService {
    @Resource
    private SysObjectDao sysObjectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysObject queryById(Integer id) {
        return this.sysObjectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysObject 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysObject> queryByPage(SysObject sysObject, PageRequest pageRequest) {
        long total = this.sysObjectDao.count(sysObject);
        return new PageImpl<>(this.sysObjectDao.queryAllByLimit(sysObject, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysObject 实例对象
     * @return 实例对象
     */
    @Override
    public SysObject insert(SysObject sysObject) {
        this.sysObjectDao.insert(sysObject);
        return sysObject;
    }

    /**
     * 修改数据
     *
     * @param sysObject 实例对象
     * @return 实例对象
     */
    @Override
    public SysObject update(SysObject sysObject) {
        this.sysObjectDao.update(sysObject);
        return this.queryById(sysObject.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysObjectDao.deleteById(id) > 0;
    }
}
