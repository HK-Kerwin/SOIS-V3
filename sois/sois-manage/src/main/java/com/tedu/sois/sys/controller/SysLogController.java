package com.tedu.sois.sys.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("log/")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("doDeleteObjects")
    public JsonResult doDeleteObjects(Integer... ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete ok");
    }

    /**
     * 根据时间删除日志信息
     * @param beginDate 开始时间
     * @param endDate 结束时间
     */
    @RequestMapping("doRemoveLogInfoByTime")
    public JsonResult doRemoveLogInfoByTime(Date beginDate,Date endDate) {
        sysLogService.removeLogInfoByTime(beginDate,endDate);
        return new JsonResult("删除成功");
    }

    /***
     * 分页查询请求处理方法(此方法由Spring MVC框架通过反射技术调用)
     * @param userName 接收客户端请求中的username值
     * @param page 页码
     * @param limit 数量
     * @return 封装了业务数据以及状态信息的一个对象(JsonResult)
     */
    @RequestMapping("doFindPageLogInfoListByUserName")
    //@ResponseBody
    public JsonResult doFindPageLogInfoListByUserName(String userName, Integer page,Integer limit) {
        return sysLogService.findPageLogInfoListByUserName(userName, page,limit);
    }
    //局部异常处理
//	 @ExceptionHandler(RuntimeException.class)
//	 @ResponseBody
//	 public JsonResult doHandleRuntimeException(
//			 RuntimeException e) {
//		 return null;
//	 }
}
