package com.tedu.sois.common.pojo.topic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;

/**
 * @author LYS On 2020/6/18 2:00.
 * 总题库里面存放多套试题
 */
@TableName("topic")
public class Topic extends BaseEntity {

    /**套题ID*/
    @TableId(type = IdType.AUTO)
    private Integer topicId;

    /**套题序号*/
    private Integer topicSequence;

    /**套题名字*/
    private String topicTitle;

    /**套题题目数量*/
    private Integer topicNum;

    /**套题题目总分*/
    private Integer topicFixedScore;

    /**套题类型ID */
    private Integer tcId;

    public Topic() {
    }

    public Topic(Integer topicId, Integer topicSequence, String topicTitle, Integer topicNum, Integer topicFixedScore, Integer tcId) {
        this.topicId = topicId;
        this.topicSequence = topicSequence;
        this.topicTitle = topicTitle;
        this.topicNum = topicNum;
        this.topicFixedScore = topicFixedScore;
        this.tcId = tcId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicSequence() {
        return topicSequence;
    }

    public void setTopicSequence(Integer topicSequence) {
        this.topicSequence = topicSequence;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Integer getTopicFixedScore() {
        return topicFixedScore;
    }

    public void setTopicFixedScore(Integer topicFixedScore) {
        this.topicFixedScore = topicFixedScore;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicSequence=" + topicSequence +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicNum=" + topicNum +
                ", topicFixedScore=" + topicFixedScore +
                ", tcId=" + tcId +
                ", remark='" + remark + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
