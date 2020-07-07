package com.tedu.sois.teacher.service;

import com.tedu.sois.common.vo.JsonResult;

/**
 * @author LYS On 2020/6/20 14:10.
 */
public interface TopicInfoService {
    /**
     * 查询当前套题ID对应的所有题目
     * @param topicId
     * @return
     */
    JsonResult findTopicInfoByTopicId(Integer topicId, Integer page, Integer limit);
}
