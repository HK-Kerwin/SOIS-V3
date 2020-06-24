package com.tedu.sois.teacher.service;


import com.tedu.sois.common.pojo.teacher.ClassInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CrmManageService {

    /**
     * 把CRM导出管理学员信息表，读取后整理写入数据库
     * @param file 传入表格文件
     * @return 受影响行数
     */
    void addCrmExcel(MultipartFile file);

    /**
     * 添加班级信息
     * @param classInfo
     */
    void SaveClassInfo(ClassInfo classInfo);

    /**
     * 根据班级名字删除信息
     * @param className
     */
    void removeClassInfoByClassName(String className);

    /**
     * 删除重复的班级信息
     */
    void removeRepeatClassInfo();

    /**
     * 查询所有的班级名称
     * @return 全部班级名集合
     */
    List<String> showClassNumAllList();

    /**
     * 通过班级名字找所属方向
     * @param className
     * @return
     */
    String findClassDirectionByClassName(String className);
}
