package com.tedu.sois.stu.service;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;

import java.util.List;

/**
 * 学员信息操作接口
 * @author LYS
 */
public interface StuBaseInfoService {

    /**
     * 插入用户信息使用mybatis-Puls 提供接口实现
     * 其中在业务层实现了注册基本信息时创建账号
     * 登录账号：注册时填入的邮箱
     * 初始登录密码：身份证后四位拼接手机号后四位
     * @param stuBaseInfo 学生信息实体对象
     * @param state 判断是新添加还是修改的状态码
     * @return 受影响行数
     */
    int saveStuBaseInfo(StuBaseInfo stuBaseInfo, int state);

    /**
     * 根据学员ID删除学员信息
     * @param stuIds
     */
    void removeStuInfoById(Long[] stuIds);

    /**
     * 修改学员信息
     * @param data 传过来的学员数据
     * @return 受影响行数
     */
    void modifyStuBaseInfo(StuBaseInfo data);


    /**
     * 查询注册过信息学生的班级名称
     * @return 班级名集合
     */
    List<String> showStuClassNumList();

    /**
     * 根据学生信息中 身份证后四位+手机号后四位 查询信息
     * @param afterFour 身份证后四位+手机号后四位
     * @return 学生信息实体对象
     */
    List<StuBaseInfo> findByAfterFour(String afterFour);

    /**
     * 通过id查询学员信息
     * @param stuId id值
     * @return 学员信息
     */
    StuBaseInfo findStuInfoById(Long stuId);


    /**
     * 老师功能:根据传进来的对象包含的属性进行查询
     * @param data 学生对象
     * @return 学生对象集合
     */
    JsonResult showStuInfoList(StuBaseInfo data, int page, int limit);


}
