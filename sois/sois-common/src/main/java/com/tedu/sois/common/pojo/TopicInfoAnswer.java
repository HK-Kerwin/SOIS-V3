package com.tedu.sois.common.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author LYS On 2020/6/18 3:18.
 * 小题题目答案
 */
@TableName("topic_info_answer")
public class TopicInfoAnswer extends BaseEntity {

    /**答案ID*/
    @TableId()
    private Integer tiaId;

    /**题目ID*/
    private Integer tiId;

    /**题目答案*/
    private String tiaAnswer;

    public TopicInfoAnswer() {
    }

    public TopicInfoAnswer(Integer tiId, String tiaAnswer) {
        this.tiId = tiId;
        this.tiaAnswer = tiaAnswer;
    }

    public Integer getTiId() {
        return tiId;
    }

    public void setTiId(Integer tiId) {
        this.tiId = tiId;
    }

    public String getTiaAnswer() {
        return tiaAnswer;
    }

    public void setTiaAnswer(String tiaAnswer) {
        this.tiaAnswer = tiaAnswer;
    }

    @Override
    public String toString() {
        return "TopicInfoAnswer{" +
                "tiId=" + tiId +
                ", tiaAnswer='" + tiaAnswer + '\'' +
                ", remark='" + remark + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
