package com.tedu.sois.teacher.controller;

import com.tedu.sois.common.pojo.Topic;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.teacher.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LYS On 2020/6/20 10:24.
 * 题库控制层
 */
@RestController
@RequestMapping("topic/")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 根据题库分类ID查询所有题库
     * @param tcId
     * @return
     */
    @RequestMapping("doFindTopicTitleList")
    public JsonResult doFindTopicTitleList(Integer tcId){
        List<Topic> topic = topicService.findTopicTitleList(tcId);
        return new JsonResult("查询成功",topic);
    }
}
