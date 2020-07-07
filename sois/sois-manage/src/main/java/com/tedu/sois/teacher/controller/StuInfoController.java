package com.tedu.sois.teacher.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.stu.service.StuBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher/")
public class StuInfoController {

    @Autowired
    private StuBaseInfoService stuBaseInfoService;

    /**
     * 根据学生ID删除用户信息
     * @param stuIds 一个或多个ID
     * @return
     */
    @RequestMapping("doRemoveStuInfoById")
    public JsonResult doRemoveStuInfoById(Long[] stuIds){
        stuBaseInfoService.removeStuInfoById(stuIds);
        return new JsonResult("删除学员信息成功");
    }

    /**
     * 分页查询所有学生信息
     * @param stuBaseInfo 查询条件封装到学生对象
     * @param page 页码
     * @param limit 查询数量
     * @return
     */
    @PostMapping("doFindStuInfoList")
    public JsonResult doFindStuInfoList(StuBaseInfo stuBaseInfo,Integer page, Integer limit ) {
        return stuBaseInfoService.showStuInfoList(stuBaseInfo,page,limit);
    }
}
