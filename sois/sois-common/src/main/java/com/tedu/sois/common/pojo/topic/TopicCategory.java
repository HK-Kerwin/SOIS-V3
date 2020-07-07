package com.tedu.sois.common.pojo.topic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author LYS On 2020/6/20 23:00.
 * 总题库里面存放多套试题
 */
@TableName("topic_category")
public class TopicCategory implements Serializable {

    /**类别ID*/
    @TableId(type = IdType.AUTO)
    private Integer tcId;

    /**套题类型编号 */
    private Integer topicTypeNum;

    /**套题类型名字 1：性格测试题 2：周考题 3：月考题*/
    private String topicTypeName;

    public TopicCategory() {
    }

    public TopicCategory(Integer tcId, Integer topicTypeNum, String topicTypeName) {
        this.tcId = tcId;
        this.topicTypeNum = topicTypeNum;
        this.topicTypeName = topicTypeName;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public Integer getTopicTypeNum() {
        return topicTypeNum;
    }

    public void setTopicTypeNum(Integer topicTypeNum) {
        this.topicTypeNum = topicTypeNum;
    }

    public String getTopicTypeName() {
        return topicTypeName;
    }

    public void setTopicTypeName(String topicTypeName) {
        this.topicTypeName = topicTypeName;
    }

    @Override
    public String toString() {
        return "TopicCategory{" +
                "tcId=" + tcId +
                ", topicTypeNum=" + topicTypeNum +
                ", topicTypeName='" + topicTypeName + '\'' +
                '}';
    }
}
