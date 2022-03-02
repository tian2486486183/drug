package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.entity.SysObjectKind;
import com.harlon.drugmanagement.service.SysObjectKindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysObjectKind)表控制层
 *
 * @author makejava
 * @since 2022-03-01 09:21:16
 */
@RestController
@RequestMapping("/kind")
public class SysObjectKindController {
    /**
     * 服务对象
     */
    @Resource
    private SysObjectKindService sysObjectKindService;

    /**
     * 分页查询
     *
     * @param sysObjectKind 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SysObjectKind>> queryByPage(SysObjectKind sysObjectKind, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysObjectKindService.queryByPage(sysObjectKind, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SysObjectKind> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sysObjectKindService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysObjectKind 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public ResponseEntity<SysObjectKind> add(SysObjectKind sysObjectKind) {
        return ResponseEntity.ok(this.sysObjectKindService.insert(sysObjectKind));
    }

    /**
     * 编辑数据
     *
     * @param sysObjectKind 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysObjectKind> edit(SysObjectKind sysObjectKind) {
        return ResponseEntity.ok(this.sysObjectKindService.update(sysObjectKind));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.sysObjectKindService.deleteById(id));
    }

}

