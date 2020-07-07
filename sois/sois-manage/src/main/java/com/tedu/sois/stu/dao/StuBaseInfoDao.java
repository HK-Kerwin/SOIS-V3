package com.tedu.sois.stu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生操作持久层对象
 * @author LYS
 */
public interface StuBaseInfoDao extends BaseMapper<StuBaseInfo> {

    /**
     * 根据学员ID删除学员信息
     * @param stuIds 一个或多个ID
     * @return
     */
    int deleteStuInfoById(@Param("stuIds") Long[] stuIds);

    /**
     * 查询注册过信息学生的班级名称
     * @return 班级名集合
     */
    List<String> selectStuClassNumList();

    /**
     * 根据身份证后四位拼接手机号后四位查询学生信息
     * @param afterFour 身份证后四位拼接手机号后四位
     * @return 返回满足条件集合
     */
    List<StuBaseInfo> selectByIdCardAndPhoneNumber(@Param("afterFour") String afterFour);

    /**
     * 根据对象中不为空的字段进行查询,统计满足条件数据条数
     * @param stuBaseInfo 学生对象
     * @return 条数
     */
    int getStuInfoRowCount(@Param("stuBaseInfo") StuBaseInfo stuBaseInfo);

    /**
     * 根据不为空的字段查询信息
     * @param stuBaseInfo 学生对象
     * @return 防止部分信息重复,用集合接收
     */
    List<StuBaseInfo> selectStuBaseInfoList (@Param("stuBaseInfo") StuBaseInfo stuBaseInfo,
                                             @Param("startIndex") int startIndex,
                                             @Param("pageSize") int pageSize);

    /**
     * 根据手机号查询信息
     * @param phoneNumber 手机号码
     * @return 学生对象
     */
    StuBaseInfo selectStuBaseInfoByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * 根据身份证查询信息
     * @param idCard 身份证号
     * @return 学生对象
     */
    StuBaseInfo selectStuBaseInfoByIdCard(@Param("idCard") String idCard);

    /**
     * 根据邮箱查询学生信息
     * @param stuEmail 邮箱
     * @return 学生对象
     */
    StuBaseInfo selectStuBaseInfoByEmail(@Param("stuEmail") String stuEmail);

    /**
     * 根据学生名字查询
     * @param stuName 学生名字
     * @return 有可能重名,所以返回学生对象集合
     */
    List<StuBaseInfo> selectStuBaseInfoByStuName(@Param("stuName") String stuName);


}
