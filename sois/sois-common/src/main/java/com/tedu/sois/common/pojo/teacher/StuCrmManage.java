package com.tedu.sois.common.pojo.teacher;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * CRM导出管理学员信息表
 * 关键信息字段
 */
@TableName("stu_crm_manage")
public class StuCrmManage implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer crmManageId;
    /**CRM编号*/
    private String crmId;
    /**学生姓名*/
    private String crmStuName;
    /**系列班級*/
    private String seriesClass;
    /**班级*/
    private String crmClass;
    /**学员状态*/
    private String stuState;
    /**合同发送类型*/
    private String contractSendType;
    /**学费总值*/
    private String needCost;
    /**累计缴费*/
    private String costAll;
    /**进班日期*/
    private Date enterClassTime;
    /**月报日期*/
    private Date monthlyTime;
    /**教学中心*/
    private String teachingCenter;
    /**销售中*/
    private String salesCenter;
    /**咨询顾问*/
    private String consultName;
    /**创建人*/
    private String createdName;
    /**创建时间*/
    private Date createdTime;


    public Integer getCrmManageId() {
        return crmManageId;
    }

    public void setCrmManageId(Integer crmManageId) {
        this.crmManageId = crmManageId;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCrmStuName() {
        return crmStuName;
    }

    public void setCrmStuName(String crmStuName) {
        this.crmStuName = crmStuName;
    }

    public String getSeriesClass() {
        return seriesClass;
    }

    public void setSeriesClass(String seriesClass) {
        this.seriesClass = seriesClass;
    }

    public String getCrmClass() {
        return crmClass;
    }

    public void setCrmClass(String crmClass) {
        this.crmClass = crmClass;
    }

    public String getStuState() {
        return stuState;
    }

    public void setStuState(String stuState) {
        this.stuState = stuState;
    }

    public String getContractSendType() {
        return contractSendType;
    }

    public void setContractSendType(String contractSendType) {
        this.contractSendType = contractSendType;
    }

    public String getNeedCost() {
        return needCost;
    }

    public void setNeedCost(String needCost) {
        this.needCost = needCost;
    }

    public String getCostAll() {
        return costAll;
    }

    public void setCostAll(String costAll) {
        this.costAll = costAll;
    }

    public Date getEnterClassTime() {
        return enterClassTime;
    }

    public void setEnterClassTime(Date enterClassTime) {
        this.enterClassTime = enterClassTime;
    }

    public Date getMonthlyTime() {
        return monthlyTime;
    }

    public void setMonthlyTime(Date monthlyTime) {
        this.monthlyTime = monthlyTime;
    }

    public String getTeachingCenter() {
        return teachingCenter;
    }

    public void setTeachingCenter(String teachingCenter) {
        this.teachingCenter = teachingCenter;
    }

    public String getSalesCenter() {
        return salesCenter;
    }

    public void setSalesCenter(String salesCenter) {
        this.salesCenter = salesCenter;
    }

    public String getConsultName() {
        return consultName;
    }

    public void setConsultName(String consultName) {
        this.consultName = consultName;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createName) {
        this.createdName = createName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuCrmManage that = (StuCrmManage) o;
        return Objects.equals(crmManageId, that.crmManageId) &&
                Objects.equals(crmId, that.crmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crmManageId, crmId);
    }

    @Override
    public String toString() {
        return "StuCrmManage{" +
                "crmManageId=" + crmManageId +
                ", crmId='" + crmId + '\'' +
                ", crmStuName='" + crmStuName + '\'' +
                ", seriesClass='" + seriesClass + '\'' +
                ", crmClass='" + crmClass + '\'' +
                ", stuState='" + stuState + '\'' +
                ", contractSendType='" + contractSendType + '\'' +
                ", needCost='" + needCost + '\'' +
                ", costAll='" + costAll + '\'' +
                ", enterClassTime=" + enterClassTime +
                ", monthlyTime=" + monthlyTime +
                ", teachingCenter='" + teachingCenter + '\'' +
                ", salesCenter='" + salesCenter + '\'' +
                ", consultName='" + consultName + '\'' +
                ", createName='" + createdName + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
