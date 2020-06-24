package com.tedu.sois.common.pojo.stu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;

import java.util.Date;

/**
 * 测试题
 * @author LYS
 */
@TableName("stu_quizzes")
public class Quizzes extends BaseEntity {

    /**试题ID*/
    @TableId(type = IdType.AUTO)
    private Long quiId;

    /**用户ID*/
    private Long userId;

    /**学员ID*/
    private Long stuId;

    /**套题ID*/
    private Integer topicId;

    /**考试开启时间*/
    private Date quiOpenTime;

    /**试题状态 0:关闭状态 1:未参考 2:等待参考 3:已批改*/
    private Integer quiStatus;

    /**得分*/
    private Double quiScore;

    /**图标标签class值*/
    private String quiIcon;

    public Quizzes() {
    }


}
