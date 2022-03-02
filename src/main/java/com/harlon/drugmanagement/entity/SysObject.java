package com.harlon.drugmanagement.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysObject)实体类
 *
 * @author makejava
 * @since 2022-03-01 09:22:38
 */
public class SysObject implements Serializable {
    private static final long serialVersionUID = 586772954956111340L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 类别id
     */
    private Integer kindId;
    /**
     * 别称
     */
    private String kindNickname;
    /**
     * 生产日期
     */
    private Date kindProductDate;
    /**
     * 有效期
     */
    private Date kindValidatePeriod;
    /**
     * 提醒日期
     */
    private Date reminderDate;
    /**
     * 图片地址
     */
    private String imgPath;
    /**
     * 物品存放位置
     */
    private String storedLocation;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 操作者用户id
     */
    private Integer operator;


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

    public String getKindNickname() {
        return kindNickname;
    }

    public void setKindNickname(String kindNickname) {
        this.kindNickname = kindNickname;
    }

    public Date getKindProductDate() {
        return kindProductDate;
    }

    public void setKindProductDate(Date kindProductDate) {
        this.kindProductDate = kindProductDate;
    }

    public Date getKindValidatePeriod() {
        return kindValidatePeriod;
    }

    public void setKindValidatePeriod(Date kindValidatePeriod) {
        this.kindValidatePeriod = kindValidatePeriod;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getStoredLocation() {
        return storedLocation;
    }

    public void setStoredLocation(String storedLocation) {
        this.storedLocation = storedLocation;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

}

