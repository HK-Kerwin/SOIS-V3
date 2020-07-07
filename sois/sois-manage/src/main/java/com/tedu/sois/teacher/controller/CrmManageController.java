package com.tedu.sois.teacher.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.teacher.ClassInfo;
import com.tedu.sois.teacher.service.CrmManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("crm/")
public class CrmManageController {

    @Autowired
    private CrmManageService crmManageService;

    /**
     * 更新CRM学生信息表数据
     * 会更新班级表和CRM学生信息表
     * @param excelFile
     * @return
     */
    @PostMapping("uploadExcel")
    public JsonResult uploadExcel(MultipartFile excelFile){
        crmManageService.addCrmExcel(excelFile);
        System.err.println("数据更新成功");
        return new JsonResult("数据更新成功");
    }

    /**
     * 添加班级信息
     * @param classInfo
     * @return
     */
    @PostMapping("doSaveClassInfo")
    public JsonResult doSaveClassInfo(ClassInfo classInfo){
        crmManageService.SaveClassInfo(classInfo);
        return new JsonResult("添加成功");
    }

    /**
     * 根据班级名字删除信息
     * @param className
     * @return
     */
    @PostMapping("doRemoveClassInfoByClassName")
    public JsonResult doRemoveClassInfoByClassName(String className){
        crmManageService.removeClassInfoByClassName(className);
        return new JsonResult("删除成功");
    }

    /**
     * 删除重复的班级信息
     */
    @RequestMapping("doRemoveRepeatClassInfo")
    public JsonResult doRemoveRepeatClassInfo(){
        crmManageService.removeRepeatClassInfo();
        return new JsonResult("删除成功");
    }


    /**
     * 查询所有班级名字
     * 后续老师自行添加
     * @return
     */
    @RequestMapping("findStuClass")
    public JsonResult findStuClass(){
        return new JsonResult(crmManageService.showClassNumAllList());
    }

    /**
     * 根据班级名字查询方向
     * @param className
     * @return
     */
    @RequestMapping("findClassDirectionByClassName")
    public JsonResult findClassDirectionByClassName(String className){
        if (className == null || "".equals(className))
            return new JsonResult("此班级没有方向,请尽快添加","");
        String direction = crmManageService.findClassDirectionByClassName(className);
        return new JsonResult(direction,direction);
    }

}
