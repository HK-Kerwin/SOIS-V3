package com.tedu.sois.teacher.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.sois.common.pojo.topic.TopicInfo;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.teacher.dao.TopicInfoDao;
import com.tedu.sois.teacher.service.TopicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LYS On 2020/6/20 14:06.
 */
@Service
public class TopicInfoServiceImpl implements TopicInfoService {

    @Autowired
    private TopicInfoDao topicInfoDao;

    @Override
    public JsonResult findTopicInfoByTopicId(Integer topicId,Integer page, Integer limit ) {

        //参数校验
        if (page < 1)
            throw new IllegalArgumentException("页码值不正确");

        Page<TopicInfo> topicInfoPage = new Page<>(page,limit);

        QueryWrapper<TopicInfo> qw = new QueryWrapper();
        Map<String,Object> map = new HashMap<>();
        map.put("topic_id",topicId);
        map.put("del_flag",0);
        qw.allEq(map);

        topicInfoPage = topicInfoDao.selectPage(topicInfoPage, qw);

        List<TopicInfo> topicInfos = topicInfoPage.getRecords();

        int total = (int)topicInfoPage.getTotal();


        return new JsonResult(page, limit, total, topicInfos);
    }
}
