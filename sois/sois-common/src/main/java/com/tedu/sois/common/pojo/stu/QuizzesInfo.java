package com.tedu.sois.common.pojo.stu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;

/**
 * @author LYS On 2020/6/18 3:58.
 * 测试题完成详情
 */
@TableName("")
public class QuizzesInfo extends BaseEntity {

    /**测试题详情ID*/
    @TableId(type = IdType.AUTO)
    private Long qiId;

    /**试题ID*/
    private Long quiId;

    /**题目ID*/
    private Integer tiId;

    /**答案ID*/
    private Integer ticId;

    /**判断结果*/
    private Boolean ticResult;

}
