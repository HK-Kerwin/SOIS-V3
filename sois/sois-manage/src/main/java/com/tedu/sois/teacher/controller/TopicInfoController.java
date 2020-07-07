package com.tedu.sois.teacher.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.teacher.service.TopicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LYS On 2020/6/20 10:24.
 * 题库控制层
 */
@RestController
@RequestMapping("topicInfo/")
public class TopicInfoController {

    @Autowired
    private TopicInfoService topicInfoService;

    /**
     * 查询当前套题ID对应的所有题目
     * @param topicId
     * @return
     */
    @RequestMapping("doFindTopicInfoByTopicId")
    public JsonResult doFindTopicInfoByTopicId(Integer topicId,Integer page, Integer limit ){
        return topicInfoService.findTopicInfoByTopicId(topicId,page,limit);
    }
}
