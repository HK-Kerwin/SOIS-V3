package com.tedu.sois.teacher.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.sois.common.pojo.topic.Topic;
import com.tedu.sois.common.pojo.topic.TopicCategory;
import com.tedu.sois.teacher.dao.TopicCategoryDao;
import com.tedu.sois.teacher.dao.TopicDao;
import com.tedu.sois.teacher.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LYS On 2020/6/20 10:28.
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicCategoryDao topicCategoryDao;


    @Override
    public List<TopicCategory> findTopicTypeList() {

        QueryWrapper<TopicCategory> qw = new QueryWrapper();

        qw.select("topic_type_num","topic_type_name");

        List<TopicCategory> topics = topicCategoryDao.selectList(qw);

        return topics;
    }

    @Override
    public List<Topic> findTopicTitleList(Integer tcId) {

        QueryWrapper<Topic> qw = new QueryWrapper();

        Map<String,Object> map = new HashMap<>();
        map.put("tc_id",tcId);
        map.put("del_flag",0);

        qw.select("topic_id","topic_title").allEq(map);

        List<Topic> topicList = topicDao.selectList(qw);

        return topicList;
    }
}
