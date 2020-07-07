package com.tedu.sois.common.vo;

import java.io.Serializable;

/**
 * @author LYS On 2020/6/18 4:04.
 * 试卷提交的工具类
 */
public class QuizzesInfoVO implements Serializable {

    /**套题ID*/
    private Integer topicId;

    /**试题ID数组*/
    private Long[] quiId;

    /**题目ID数组*/
    private Integer[] tiId;

    /**答案ID数组*/
    private Integer[] ticId;

}
