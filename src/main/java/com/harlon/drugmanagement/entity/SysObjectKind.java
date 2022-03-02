package com.harlon.drugmanagement.entity;

import java.io.Serializable;

/**
 * (SysObjectKind)实体类
 *
 * @author makejava
 * @since 2022-03-01 09:21:16
 */
public class SysObjectKind implements Serializable {
    private static final long serialVersionUID = -57337194188300630L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 种类id
     */
    private Integer kindId;
    /**
     * 种类名称
     */
    private String kindName;
    /**
     * 添加者用户id
     */
    private Integer operateor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Integer getOperateor() {
        return operateor;
    }

    public void setOperateor(Integer operateor) {
        this.operateor = operateor;
    }

}

