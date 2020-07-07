package com.tedu.sois.teacher.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.teacher.ClassRoom;
import com.tedu.sois.teacher.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教室信息相关业务控制层
 * @author LYS 
 */

@RestController
@RequestMapping("classRoom/")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    /**
     * 新增教室信息
     * @param entity
     */
    @PostMapping("doSaveClassRoomInfo")
    public JsonResult doSaveClassRoomInfo(ClassRoom entity){
        classRoomService.saveClassRoomInfo(entity);
        return new JsonResult("添加教室信息成功");
    }

    /**
     * 根据一个或多个ID删除教室信息
     * @param classRoomIds
     */
    @RequestMapping("doRemoveClassRoomInfoByIds")
    public JsonResult doRemoveClassRoomInfoByIds(Integer[] classRoomIds) {
        classRoomService.removeClassRoomInfoByIds(classRoomIds);
        return new JsonResult("删除教室信息成功");
    }

    /**
     * 修改教室信息
     * @param entity
     */
    @PostMapping("doModifyClassRoomInfo")
    public JsonResult doModifyClassRoomInfo(ClassRoom entity) {
        classRoomService.modifyClassRoomInfo(entity);
        return new JsonResult("修改教室信息成功");
    }


    /**
     * 分页查询教室信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("doFindPageClassRoomInfoList")
    public JsonResult doFindPageClassRoomInfoList(Integer page, Integer limit) {
        return classRoomService.findPageClassRoomInfoList(page,limit);
    }


}
