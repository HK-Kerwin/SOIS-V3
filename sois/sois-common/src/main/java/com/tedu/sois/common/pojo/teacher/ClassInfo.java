package com.tedu.sois.common.pojo.teacher;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;

import java.util.Objects;

/**
 * 班级表
 */
@TableName("class_table")
public class ClassInfo extends BaseEntity {

    /**自增id*/
    @TableId(type = IdType.AUTO)
    private Integer classId;

    /**班级*/
    private String className;

    /**方向*/
    private String classDirection;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String classNum) {
        className = classNum;
    }

    public String getClassDirection() {
        return classDirection;
    }

    public void setClassDirection(String classDirection) {
        this.classDirection = classDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo that = (ClassInfo) o;
        return Objects.equals(classId, that.classId) &&
                Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className);
    }

    @Override
    public String toString() {
        return "ClassTable{" +
                "classId=" + classId +
                ", ClassNum='" + className + '\'' +
                ", classDirection='" + classDirection + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
