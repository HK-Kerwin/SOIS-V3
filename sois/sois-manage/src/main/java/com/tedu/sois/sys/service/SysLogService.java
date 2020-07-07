package com.tedu.sois.sys.service;

import com.tedu.sois.common.pojo.sys.SysLog;
import com.tedu.sois.common.vo.JsonResult;

import java.util.Date;

/**
 * 日志业务处理接口对象
 */
public interface SysLogService {
	
	/**
	 * 保存用户行为日志
	 * @param entity
	 */
	void saveLogInfo(SysLog entity);
	
	/**
	  *  执行删除业务
	 * @param ids 多个日志id
	 * @return 删除的行数
	 */
	void deleteObjects(Integer... ids);

	/**
	 * 根据时间删除日志信息
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 */
	void removeLogInfoByTime(Date beginDate, Date endDate);
	
     /**
            * 分页查询当前页记录
      * @param userName 查询条件
	  * @param page 页码
	  * @param limit 数量
      * @return 封装了分页信息和当前页记录的值对象
      */
	 JsonResult findPageLogInfoListByUserName(String userName, Integer page, Integer limit);

}
