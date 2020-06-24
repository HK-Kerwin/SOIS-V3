package com.tedu.sois.common.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class BaseEntity implements Serializable {

    /**备注*/
    protected String remark;

    /**创建日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createdTime;

    /**更新日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date modifiedTime;

    /**创建者*/
    protected String createdUser;

    /**更新者*/
    protected String modifiedUser;

    /**删除标记 删除标志（0代表存在 2代表删除）*/
    protected String delFlag;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
