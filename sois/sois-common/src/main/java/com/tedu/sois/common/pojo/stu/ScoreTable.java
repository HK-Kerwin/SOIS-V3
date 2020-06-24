package com.tedu.sois.common.pojo.stu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

/**
 * 成绩表
 */
@TableName("score_table")
public class ScoreTable implements Serializable {
    /*自增id*/
    @TableId(type = IdType.AUTO)
    private Integer scoreId;

    /*学员id*/
    private Integer stuId;

    /*学生姓名*/
    private String stuName;

    /*第一次月考成绩*/
    private Double monthOne;
    @TableField(exist = false)
    private String rowM1;

    /*第二次月考成绩*/
    private Double monthTwo;
    @TableField(exist = false)
    private String rowM2;

    /*第三次月考成绩*/
    private Double monthThree;
    @TableField(exist = false)
    private String rowM3;

    /*第一次周考成绩*/
    private Double weekOne;
    @TableField(exist = false)
    private String rowW1;

    /*第二次周考成绩*/
    private Double weekTwo;
    @TableField(exist = false)
    private String rowW2;

    /*第三次周考成绩*/
    private Double weekThree;
    @TableField(exist = false)
    private String rowW3;

    /*第四次周考成绩*/
    private Double weekFour;
    @TableField(exist = false)
    private String rowW4;

    /*第五次周考成绩*/
    private Double weekFive;
    @TableField(exist = false)
    private String rowW5;

    /*第六次周考成绩*/
    private Double weekSix;
    @TableField(exist = false)
    private String rowW6;

    /*第七次周考成绩*/
    private Double weekSeven;
    @TableField(exist = false)
    private String rowW7;

    /*第八次周考成绩*/
    private Double weekEight;
    @TableField(exist = false)
    private String rowW8;

    /*其他考试成绩*/
    private Double otherScoreOne;
    @TableField(exist = false)
    private String rowOther1;

    /*总成绩*/
    @TableField(exist = false)
    private Double sumScore;

    /*方向*/
    @TableField(exist = false)
    private String direction;

    /*教室号*/
    @TableField(exist = false)
    private String classRoom;

    /*班级号*/
    @TableField(exist = false)
    private String stuClass;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Double getMonthOne() {
        return monthOne;
    }

    public void setMonthOne(Double monthOne) {
        this.monthOne = monthOne;
    }

    public String getRowM1() {
        return rowM1;
    }

    public void setRowM1(String rowM1) {
        this.rowM1 = rowM1;
    }

    public Double getMonthTwo() {
        return monthTwo;
    }

    public void setMonthTwo(Double monthTwo) {
        this.monthTwo = monthTwo;
    }

    public String getRowM2() {
        return rowM2;
    }

    public void setRowM2(String rowM2) {
        this.rowM2 = rowM2;
    }

    public Double getMonthThree() {
        return monthThree;
    }

    public void setMonthThree(Double monthThree) {
        this.monthThree = monthThree;
    }

    public String getRowM3() {
        return rowM3;
    }

    public void setRowM3(String rowM3) {
        this.rowM3 = rowM3;
    }

    public Double getWeekOne() {
        return weekOne;
    }

    public void setWeekOne(Double weekOne) {
        this.weekOne = weekOne;
    }

    public String getRowW1() {
        return rowW1;
    }

    public void setRowW1(String rowW1) {
        this.rowW1 = rowW1;
    }

    public Double getWeekTwo() {
        return weekTwo;
    }

    public void setWeekTwo(Double weekTwo) {
        this.weekTwo = weekTwo;
    }

    public String getRowW2() {
        return rowW2;
    }

    public void setRowW2(String rowW2) {
        this.rowW2 = rowW2;
    }

    public Double getWeekThree() {
        return weekThree;
    }

    public void setWeekThree(Double weekThree) {
        this.weekThree = weekThree;
    }

    public String getRowW3() {
        return rowW3;
    }

    public void setRowW3(String rowW3) {
        this.rowW3 = rowW3;
    }

    public Double getWeekFour() {
        return weekFour;
    }

    public void setWeekFour(Double weekFour) {
        this.weekFour = weekFour;
    }

    public String getRowW4() {
        return rowW4;
    }

    public void setRowW4(String rowW4) {
        this.rowW4 = rowW4;
    }

    public Double getWeekFive() {
        return weekFive;
    }

    public void setWeekFive(Double weekFive) {
        this.weekFive = weekFive;
    }

    public String getRowW5() {
        return rowW5;
    }

    public void setRowW5(String rowW5) {
        this.rowW5 = rowW5;
    }

    public Double getWeekSix() {
        return weekSix;
    }

    public void setWeekSix(Double weekSix) {
        this.weekSix = weekSix;
    }

    public String getRowW6() {
        return rowW6;
    }

    public void setRowW6(String rowW6) {
        this.rowW6 = rowW6;
    }

    public Double getWeekSeven() {
        return weekSeven;
    }

    public void setWeekSeven(Double weekSeven) {
        this.weekSeven = weekSeven;
    }

    public String getRowW7() {
        return rowW7;
    }

    public void setRowW7(String rowW7) {
        this.rowW7 = rowW7;
    }

    public Double getWeekEight() {
        return weekEight;
    }

    public void setWeekEight(Double weekEight) {
        this.weekEight = weekEight;
    }

    public String getRowW8() {
        return rowW8;
    }

    public void setRowW8(String rowW8) {
        this.rowW8 = rowW8;
    }

    public Double getOtherScoreOne() {
        return otherScoreOne;
    }

    public void setOtherScoreOne(Double otherScoreOne) {
        this.otherScoreOne = otherScoreOne;
    }

    public String getRowOther1() {
        return rowOther1;
    }

    public void setRowOther1(String rowOther1) {
        this.rowOther1 = rowOther1;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreTable that = (ScoreTable) o;
        return Objects.equals(scoreId, that.scoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreId);
    }

    @Override
    public String toString() {
        return "ScoreTable{" +
                "scoreId=" + scoreId +
                ", stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", monthOne=" + monthOne +
                ", rowM1='" + rowM1 + '\'' +
                ", monthTwo=" + monthTwo +
                ", rowM2='" + rowM2 + '\'' +
                ", monthThree=" + monthThree +
                ", rowM3='" + rowM3 + '\'' +
                ", weekOne=" + weekOne +
                ", rowW1='" + rowW1 + '\'' +
                ", weekTwo=" + weekTwo +
                ", rowW2='" + rowW2 + '\'' +
                ", weekThree=" + weekThree +
                ", rowW3='" + rowW3 + '\'' +
                ", weekFour=" + weekFour +
                ", rowW4='" + rowW4 + '\'' +
                ", weekFive=" + weekFive +
                ", rowW5='" + rowW5 + '\'' +
                ", weekSix=" + weekSix +
                ", rowW6='" + rowW6 + '\'' +
                ", weekSeven=" + weekSeven +
                ", rowW7='" + rowW7 + '\'' +
                ", weekEight=" + weekEight +
                ", rowW8='" + rowW8 + '\'' +
                ", otherScoreOne=" + otherScoreOne +
                ", rowOther1='" + rowOther1 + '\'' +
                ", sumScore=" + sumScore +
                ", direction='" + direction + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", stuClass='" + stuClass + '\'' +
                '}';
    }
}
