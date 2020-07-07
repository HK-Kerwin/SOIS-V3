package com.tedu.sois.teacher.service;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.teacher.ClassRoom;
import com.tedu.sois.common.pojo.teacher.ClassRoomCountVo;


public interface ClassRoomService {

    /**
     * 新增教室信息
     * @param entity
     */
    void saveClassRoomInfo(ClassRoom entity);

    /**
     * 根据一个或多个ID删除教室信息
     * @param classRoomIds
     */
    void removeClassRoomInfoByIds(Integer[] classRoomIds);

    /**
     * 修改教室信息
     * @param entity
     */
    void modifyClassRoomInfo(ClassRoom entity);

    /**
     * 分页查询教室信息
     * @param page
     * @param limit
     * @return
     */
    JsonResult findPageClassRoomInfoList(Integer page, Integer limit);


    /**
     * 教室数据统计
     */
    ClassRoomCountVo getClassRoomStatistical();
}
