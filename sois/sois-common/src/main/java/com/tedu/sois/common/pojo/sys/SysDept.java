package com.tedu.sois.common.pojo.sys;

import com.tedu.sois.common.pojo.BaseEntity;

/**
 * 部门实体对象
 */
public class SysDept extends BaseEntity {

    /**部门编号*/
    private Integer deptId;

    /**部门名字*/
    private String deptName;

    /**部门负责人*/
    private String leader;

    /**上机部门编号*/
    private Integer parentId;

    /**排序号*/
    private Integer sort;

    /**备注*/
    private String note;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", leader='" + leader + '\'' +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", note='" + note + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
