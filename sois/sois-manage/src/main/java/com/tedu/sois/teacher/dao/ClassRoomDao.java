package com.tedu.sois.teacher.dao;

import com.tedu.sois.common.pojo.teacher.ClassRoom;
import com.tedu.sois.common.pojo.teacher.ClassRoomCountVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassRoomDao {

    /**
     * 新增教室信息
     * @param entity
     * @return
     */
    int insertClassRoomInfo(ClassRoom entity);

    /**
     * 根据一个或多个ID删除教室信息
     * @param classRoomIds
     * @return
     */
    int deleteClassRoomInfoByIds(@Param("classRoomIds") Integer[] classRoomIds);

    /**
     * 修改教室信息
     * @param entity
     * @return
     */
    int updateClassRoomInfo(ClassRoom entity);

    /**
     * 获取所有教室信息条数
     * @return
     */
    @Select("select count(*) from class_room")
    int getClassRoomCount();

    /**
     * 分页查询所有教室信息
     * @param startIndex
     * @param limit
     * @return
     */
    List<ClassRoom> selectPageClassRoomInfoList(@Param("startIndex") int startIndex, @Param("limit") Integer limit);

    /**
     * 教室数据统计
     * @return
     */
    ClassRoomCountVo selectClassRoomStatistical();
}
