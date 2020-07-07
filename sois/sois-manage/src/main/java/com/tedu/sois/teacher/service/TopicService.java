package com.tedu.sois.teacher.service;

import com.tedu.sois.common.pojo.topic.Topic;
import com.tedu.sois.common.pojo.topic.TopicCategory;

import java.util.List;

public interface TopicService {

    /**
     * 查询题库分类
     * @return
     */
    List<TopicCategory> findTopicTypeList();

    /**
     * 根据题库分类ID查询所有题库
     * @param tcId 题库分类
     * @return
     */
    List<Topic> findTopicTitleList(Integer tcId);
}
