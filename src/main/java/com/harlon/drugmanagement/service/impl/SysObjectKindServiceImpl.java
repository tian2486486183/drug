package com.harlon.drugmanagement.service.impl;

import com.harlon.drugmanagement.entity.SysObjectKind;
import com.harlon.drugmanagement.dao.SysObjectKindDao;
import com.harlon.drugmanagement.service.SysObjectKindService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysObjectKind)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 09:21:20
 */
@Service("sysObjectKindService")
public class SysObjectKindServiceImpl implements SysObjectKindService {
    @Resource
    private SysObjectKindDao sysObjectKindDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysObjectKind queryById(Integer id) {
        return this.sysObjectKindDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysObjectKind 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysObjectKind> queryByPage(SysObjectKind sysObjectKind, PageRequest pageRequest) {
        long total = this.sysObjectKindDao.count(sysObjectKind);
        return new PageImpl<>(this.sysObjectKindDao.queryAllByLimit(sysObjectKind, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysObjectKind 实例对象
     * @return 实例对象
     */
    @Override
    public SysObjectKind insert(SysObjectKind sysObjectKind) {
        this.sysObjectKindDao.insert(sysObjectKind);
        return sysObjectKind;
    }

    /**
     * 修改数据
     *
     * @param sysObjectKind 实例对象
     * @return 实例对象
     */
    @Override
    public SysObjectKind update(SysObjectKind sysObjectKind) {
        this.sysObjectKindDao.update(sysObjectKind);
        return this.queryById(sysObjectKind.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysObjectKindDao.deleteById(id) > 0;
    }
}
