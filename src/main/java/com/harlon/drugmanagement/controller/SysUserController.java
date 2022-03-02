package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2022-03-01 09:30:55
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SysUser>> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysUserService.queryByPage(sysUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SysUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.sysUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public ResponseEntity<SysUser> add(SysUser sysUser) {
        return ResponseEntity.ok(this.sysUserService.insert(sysUser));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysUser> edit(SysUser sysUser) {
        return ResponseEntity.ok(this.sysUserService.update(sysUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.sysUserService.deleteById(id));
    }

}

