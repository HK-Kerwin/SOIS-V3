package com.tedu.sois.teacher.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.sois.common.pojo.teacher.ClassInfo;
import com.tedu.sois.common.pojo.teacher.StuCrmManage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface StuCrmManageDao extends BaseMapper<StuCrmManage> {

    /**
     * 把CRM导出管理学员信息表写入数据库
     * CRM的编号作为索引避免导入重复数据
     * @param list SysStuCrmManage 集合
     * @return 受影响行数
     */
    Integer insertStuCrmManage(List<StuCrmManage> list);

    /**
     * 添加班级信息
     * @return
     */
    int insertClassInfo(ClassInfo classInfo);

    /**
     * 批量插入班级信息
     * @return
     */
    int insertStuClassSet(Set<ClassInfo> classInfo);

    /**
     * 根据班级名字删除信息
     * @param className
     * @return
     */
    @Delete("delete from class_info where class_name=#{className}")
    int deleteClassInfoByClassName(String className);

    /**
     * 删除重复的班级信息
     * @return
     */
    int deleteRepeatClassInfo();

    /**
     * 查询所有班级名称
     * @return 班级名称集合
     */
    List<String> selectClassNameAllList();

    /**
     * 根据班级名字查询方向
     * @param className
     * @return
     */
    String selectClassDirectionByClassName(String className);

    /**
     * 根据班级名字统计记录
     * @param className
     * @return
     */
    @Select("select count(*) from class_info where class_name=#{className}")
    int selectClassInfoByClassName(String className);
}
