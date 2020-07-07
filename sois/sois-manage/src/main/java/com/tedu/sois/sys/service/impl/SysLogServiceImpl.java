package com.tedu.sois.sys.service.impl;

import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.sys.dao.SysLogDao;
import com.tedu.sois.common.pojo.sys.SysLog;
import com.tedu.sois.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * @Async 描述的方法表示这个方法要在独立于
     * web服务器的外部线程中进行工作.
     */
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveLogInfo(SysLog entity) {
        System.out.println("SysLogServiceImpl.saveLogInfo().thread.name:" + Thread.currentThread().getName());
        sysLogDao.insert(entity);
        //sysLogDao.insertLogInfo(entity);
    }

    //@RequiresPermisssion("sys:log:delete")
    @Override
    public void deleteObjects(Integer... ids) {
        //1.验证参数的有效性
        if (ids == null || ids.length == 0)
            throw new IllegalArgumentException("请选择删除的记录");
        //2.执行删除操作
        int rows = sysLogDao.deleteLogsInfo(ids);
        //3.验证并返回结果
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
    }

    @Override
    public void removeLogInfoByTime(Date beginDate, Date endDate) {
        int rows = sysLogDao.deleteLogInfoByTime(beginDate, endDate);
        if (rows == 0)
            throw new ServiceException("删除失败");
    }

    @Override
    public JsonResult findPageLogInfoListByUserName(String userName, Integer page, Integer limit) {
        //1.对参数校验
        if (page < 1)
            throw new IllegalArgumentException("页码值不正确");
        //2.基于条件查询总记录数并进行校验
        int rowCount = sysLogDao.getLogsInfoRowCount(userName);
        if (rowCount == 0)
            throw new ServiceException("记录不存在");
        //3.基于条件查询当前页记录
        int startIndex = (page - 1) * limit;
        List<SysLog> records = sysLogDao.selectPageLogsInfo(userName, startIndex, limit);
        //4.对查询结果进行封装并返回
        return new JsonResult(page, limit, rowCount, records);
    }

}





