package com.harlon.drugmanagement.service.impl;

import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.dao.SysUserDao;
import com.harlon.drugmanagement.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-02-25 21:59:45
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;




    /**
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public SysUser queryByUsername(String username) {
        return sysUserDao.queryUserByUsername(username);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        long total = this.sysUserDao.count(sysUser);
        return new PageImpl<>(this.sysUserDao.queryAllByLimit(sysUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }
}
