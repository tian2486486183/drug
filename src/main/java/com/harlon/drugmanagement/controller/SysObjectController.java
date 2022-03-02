package com.harlon.drugmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.google.zxing.WriterException;
import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.entity.SysObject;
import com.harlon.drugmanagement.enums.RespSystemEnum;
import com.harlon.drugmanagement.service.SysObjectService;
import com.harlon.drugmanagement.utils.OSSUtil;
import com.harlon.drugmanagement.utils.QRCodeUtil;
import com.harlon.drugmanagement.vo.LoginUser;
import com.harlon.drugmanagement.vo.RespBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * (SysObject)表控制层
 *
 * @author makejava
 * @since 2022-03-01 09:22:38
 */
@RestController
@RequestMapping("/object")
public class SysObjectController {
    /**
     * 服务对象
     */
    @Resource
    private SysObjectService sysObjectService;



    /**
     * 分页查询
     *
     * @param sysObject 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SysObject>> queryByPage(SysObject sysObject, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysObjectService.queryByPage(sysObject, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SysObject> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sysObjectService.queryById(id));
    }

    /**
     * 新增数据
     * ResponseEntity.ok(this.sysObjectService.insert(sysObject));
     * @param sysObject 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public RespBean add(@RequestBody SysObject sysObject) {
        Date date = new Date(System.currentTimeMillis());
        sysObject.setCreateDate(date);
        sysObject.setUpdateDate(date);
        //解析token获取存储者id
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) authenticationToken.getPrincipal();
        sysObject.setOperator(Integer.parseInt(""+user.getUser().getId()));
        //生成二维码，并且存储至OSS
        String content = sysObject.toString();
        String objectName = RedisConst.OBJECT_NAME+sysObject.getKindId()+".jpg";
        sysObject.setImgPath(objectName);
        OSSUtil.upload(objectName,content);
        sysObjectService.insert(sysObject);
        return RespBean.success(RespSystemEnum.SUCCESS);
    }

    /**
     * 编辑数据
     *
     * @param sysObject 实体
     * @return 编辑结果
     */
    @PutMapping("update")
    public ResponseEntity<SysObject> edit(SysObject sysObject) {
        return ResponseEntity.ok(this.sysObjectService.update(sysObject));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.sysObjectService.deleteById(id));
    }

}

