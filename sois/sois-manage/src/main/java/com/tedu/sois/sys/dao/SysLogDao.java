package com.tedu.sois.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.sois.common.pojo.sys.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface SysLogDao extends BaseMapper<SysLog> {
    /**
     * 用于持久化用户行为日志信息
     * @param entity
     * @return
     */
    //int insertLogInfo(SysLog entity);

    /**
     * 基于多个日志记录id,进行日志删除.
     *
     * @param lgoIds 用于存储多个记录id
     * @return 删除的行数
     */
    int deleteLogsInfo(@Param("lgoIds") Integer... lgoIds);

    /**
     * 根据时间删除日志信息
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     */
    @Delete("delete from sys_log where created_time between #{beginDate} and #{endDate}")
    int deleteLogInfoByTime(Date beginDate, Date endDate);

    /**
     * 基于条件统计记录总数
     *
     * @return 具体行数
     * @Param 注解可以描述数据层方法参数, 尤其是
     * 当方法有多个参数或者参数可能会用在动态sql中
     */
    int getLogsInfoRowCount(@Param("userName") String userName);

    /**
     * 查询当前页记录
     *
     * @param userName   查询条件,允许为空
     * @param startIndex 表示起始位置
     * @param limit      页面大小
     * @return 当前页记录
     */
    List<SysLog> selectPageLogsInfo(
            @Param("userName") String userName,
            @Param("startIndex") Integer startIndex,
            @Param("limit") Integer limit);//param1,param2,param3

}





