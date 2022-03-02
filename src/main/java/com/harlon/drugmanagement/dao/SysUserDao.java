package com.harlon.drugmanagement.dao;

import com.harlon.drugmanagement.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-25 21:59:43
 */
@Repository()
@Mapper
public interface SysUserDao {

    /**
     * 通过用户名查询数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    SysUser queryUserByUsername(String username);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysUser 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(SysUser sysUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysUser 查询条件
     * @return 总行数
     */
    long count(SysUser sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUser> entities);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

