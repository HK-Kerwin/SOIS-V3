package com.tedu.sois.common.pojo.stu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * 学员基本信息表
 */
@TableName("stu_base_info")
public class StuBaseInfo extends BaseEntity {

    /**自增序列*/
    @TableId(type = IdType.AUTO)
    private Long stuId;
    /**上传头像地址*/
    private String avatar;
    /**班级*/
    private String stuClass;
    /**学生姓名*/
    private String stuName;
    /**手机*/
    private String phoneNumber;
    /**QQ*/
    private String stuEmail;
    /**微信*/
    private String wechat;
    /**身份证*/
    private String idCard;
    /**性别*/
    private String gender;
    /**出生日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    /**籍贯*/
    private String nativePlace;
    /**婚姻状况*/
    private String maritalStatus;

    /**学历*/
    private String educationBackground;
    /**学历性质*/
    private String ebDirection;
    /**学信网可查*/
    private String ebReal;
    /**双证具备*/
    private String ebDouble;
    /**英语水平	*/
    private String englishLevel;
    /**毕业学校*/
    private String graduationSchool;
    /**专业*/
    private String professional;
    /**毕业时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduationTime;

    /**曾经岗位*/
    private String oldJobs;
    /**工资*/
    private String oldJobsSalary;
    /**工作多少月*/
    private Double oldJobsMonth;

    /**紧急联系人1*/
    private String urgentContactP1;
    /**与本人的关系*/
    private String p1Relation;
    /**紧急联系人电话1*/
    private String p1PhoneNumber;
    /**紧急联系人2*/
    private String urgentContactP2;
    /**与本人的关系*/
    private String p2Relation;
    /**紧急联系人电话2*/
    private String p2PhoneNumber;

    /**教室号*/
    private String classRoom;
    /**所属中心*/
    private String stuWhere;
    /**学员状态 在读 毕业 就业*/
    private String stuState;

    //父类中包含了
    /**创建日期*/
//    private Date createdTime;
    /**更新日期*/
//    private Date modifiedTime;
    /**删除标记*/
//    private String delFlag;


    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getEbDirection() {
        return ebDirection;
    }

    public void setEbDirection(String ebDirection) {
        this.ebDirection = ebDirection;
    }

    public String getEbReal() {
        return ebReal;
    }

    public void setEbReal(String ebReal) {
        this.ebReal = ebReal;
    }

    public String getEbDouble() {
        return ebDouble;
    }

    public void setEbDouble(String ebDouble) {
        this.ebDouble = ebDouble;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getOldJobs() {
        return oldJobs;
    }

    public void setOldJobs(String oldJobs) {
        this.oldJobs = oldJobs;
    }

    public String getOldJobsSalary() {
        return oldJobsSalary;
    }

    public void setOldJobsSalary(String oldJobsSalary) {
        this.oldJobsSalary = oldJobsSalary;
    }

    public Double getOldJobsMonth() {
        return oldJobsMonth;
    }

    public void setOldJobsMonth(Double oldJobsMonth) {
        this.oldJobsMonth = oldJobsMonth;
    }

    public String getUrgentContactP1() {
        return urgentContactP1;
    }

    public void setUrgentContactP1(String urgentContactP1) {
        this.urgentContactP1 = urgentContactP1;
    }

    public String getP1Relation() {
        return p1Relation;
    }

    public void setP1Relation(String p1Relation) {
        this.p1Relation = p1Relation;
    }

    public String getP1PhoneNumber() {
        return p1PhoneNumber;
    }

    public void setP1PhoneNumber(String p1PhoneNumber) {
        this.p1PhoneNumber = p1PhoneNumber;
    }

    public String getUrgentContactP2() {
        return urgentContactP2;
    }

    public void setUrgentContactP2(String urgentContactP2) {
        this.urgentContactP2 = urgentContactP2;
    }

    public String getP2Relation() {
        return p2Relation;
    }

    public void setP2Relation(String p2Relation) {
        this.p2Relation = p2Relation;
    }

    public String getP2PhoneNumber() {
        return p2PhoneNumber;
    }

    public void setP2PhoneNumber(String p2PhoneNumber) {
        this.p2PhoneNumber = p2PhoneNumber;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getStuWhere() {
        return stuWhere;
    }

    public void setStuWhere(String stuWhere) {
        this.stuWhere = stuWhere;
    }

    public String getStuState() {
        return stuState;
    }

    public void setStuState(String stuState) {
        this.stuState = stuState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuBaseInfo that = (StuBaseInfo) o;
        return Objects.equals(stuId, that.stuId) &&
                Objects.equals(idCard, that.idCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, idCard);
    }

    @Override
    public String toString() {
        return "StuBaseInfo{" +
                "stuId=" + stuId +
                ", avatar='" + avatar + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuName='" + stuName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", wechat='" + wechat + '\'' +
                ", idCard='" + idCard + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", nativePlace='" + nativePlace + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", educationBackground='" + educationBackground + '\'' +
                ", ebDirection='" + ebDirection + '\'' +
                ", ebReal='" + ebReal + '\'' +
                ", ebDouble='" + ebDouble + '\'' +
                ", englishLevel='" + englishLevel + '\'' +
                ", graduationSchool='" + graduationSchool + '\'' +
                ", professional='" + professional + '\'' +
                ", graduationTime=" + graduationTime +
                ", oldJobs='" + oldJobs + '\'' +
                ", oldJobsSalary='" + oldJobsSalary + '\'' +
                ", oldJobsMonth=" + oldJobsMonth +
                ", urgentContactP1='" + urgentContactP1 + '\'' +
                ", p1Relation='" + p1Relation + '\'' +
                ", p1PhoneNumber='" + p1PhoneNumber + '\'' +
                ", urgentContactP2='" + urgentContactP2 + '\'' +
                ", p2Relation='" + p2Relation + '\'' +
                ", p2PhoneNumber='" + p2PhoneNumber + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", stuWhere='" + stuWhere + '\'' +
                ", stuState='" + stuState + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
