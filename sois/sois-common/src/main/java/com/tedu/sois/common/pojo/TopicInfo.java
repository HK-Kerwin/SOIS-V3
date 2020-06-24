package com.tedu.sois.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author LYS On 2020/6/18 2:00.
 * 题目信息
 */
@TableName("topic_info")
public class TopicInfo extends BaseEntity {

    /**题目ID*/
    @TableId(type = IdType.AUTO)
    private Long tiId;

    /**套题ID*/
    private Integer topicId;

    /**题目序号*/
    private Integer tiSequence;

    /**题目内容*/
    private String tiQuestion;

    /**题目固定分数*/
    private String tiFixedScore;

    /**题目解析*/
    private String tiParsing;

    /**题目类型 1:单选  2:多选 */
    private Integer tiType;

    /**排序号 可以根据题目难度进行简单分类排序*/
    private Integer tiSort;

    public TopicInfo() {
    }

    public TopicInfo(Long tiId, Integer topicId, Integer tiSequence, String tiQuestion, String tiFixedScore, String tiParsing, Integer tiType, Integer tiSort) {
        this.tiId = tiId;
        this.topicId = topicId;
        this.tiSequence = tiSequence;
        this.tiQuestion = tiQuestion;
        this.tiFixedScore = tiFixedScore;
        this.tiParsing = tiParsing;
        this.tiType = tiType;
        this.tiSort = tiSort;
    }

    public Long getTiId() {
        return tiId;
    }

    public void setTiId(Long tiId) {
        this.tiId = tiId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTiSequence() {
        return tiSequence;
    }

    public void setTiSequence(Integer tiSequence) {
        this.tiSequence = tiSequence;
    }

    public String getTiQuestion() {
        return tiQuestion;
    }

    public void setTiQuestion(String tiQuestion) {
        this.tiQuestion = tiQuestion;
    }

    public String getTiFixedScore() {
        return tiFixedScore;
    }

    public void setTiFixedScore(String tiFixedScore) {
        this.tiFixedScore = tiFixedScore;
    }

    public String getTiParsing() {
        return tiParsing;
    }

    public void setTiParsing(String tiParsing) {
        this.tiParsing = tiParsing;
    }

    public Integer getTiType() {
        return tiType;
    }

    public void setTiType(Integer tiType) {
        this.tiType = tiType;
    }

    public Integer getTiSort() {
        return tiSort;
    }

    public void setTiSort(Integer tiSort) {
        this.tiSort = tiSort;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "tiId=" + tiId +
                ", topicId=" + topicId +
                ", tiSequence=" + tiSequence +
                ", tiQuestion='" + tiQuestion + '\'' +
                ", tiFixedScore='" + tiFixedScore + '\'' +
                ", tiParsing='" + tiParsing + '\'' +
                ", tiType=" + tiType +
                ", tiSort=" + tiSort +
                ", remark='" + remark + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
