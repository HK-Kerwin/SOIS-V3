package com.tedu.sois.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.sois.annotation.RequiredLog;
import com.tedu.sois.common.pojo.IPGeolocation;
import com.tedu.sois.common.pojo.sys.SysLog;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.common.util.IPUtils;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.sys.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;


//@Order(1)

@Aspect
@Component
public class SysLogAspect {//SysLogAspect.class
    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);
    //@Pointcut注解用于描述方法,定义切入点
    //bean(sysUserServiceImpl)为一种切入点表达式
    //sysUserServiceImpl为spring容器中的一个bean的名字
    //切入点表达式中的bean表达式
    //@Pointcut("bean(sysUserServiceImpl)")
    //@Pointcut("bean("*ServiceImpl")
    //切入点表达式中的注解表达式
    //由此注解描述的方法作为切入点.
    @Pointcut("@annotation(com.tedu.sois.annotation.RequiredLog)")
    public void logPointCut() {
    }

    //@Around为一个环绕通知
    //特点
    //1)可以在目标方法执行之前和之后添加自己的拓展业务
    //2)方法返回值类型Object
    //3)方法参数类型为ProceedingJoinPoint,此对象中封装了目标方法信息
    //4)方法异常抛出为Throwable类型
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录目标方法开始执行时间
        long t1 = System.currentTimeMillis();
        LOGGER.info("start time:" + t1);
        Object result;
        try {
            //2.执行目标方法
            result = joinPoint.proceed();//假如有下一个切面先执行切面对象方法
            //3.记录目标方法结束执行时间
            long t2 = System.currentTimeMillis();
            LOGGER.info("end time:" + t2);
            //记录用户行为日志
            saveLog(joinPoint, (t2 - t1));
        }catch (Throwable e){
            LOGGER.info("error is {} " + e.getMessage());
            throw  e;
        }
        return result;
    }

    @Autowired
    private SysLogService logService;

    //获取用户行为日志信息,然后将日志写入到数据库
    private void saveLog(ProceedingJoinPoint joinPoint, long millisecond) throws Exception {
        //1.获取日志信息
        //获取目标方法对象
        Method targetMethod = getTargetMethod(joinPoint);
        //获取目标方法名:目标类全名+方法名
        String classMethodName = getTargetMethodName(targetMethod);
        //获取操作名
        String operation = getOperation(targetMethod);
        //String params=Arrays.toString(joinPoint.getArgs());
        //获取方法执行时的实际参数
        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());//借助了jackson中的api
        //2.封装日志信息
        SysUser user = ShiroUtils.getUser();
        String userName = "未登录";
        if(user != null){
            userName = user.getUserName();
        }
        String ip = IPUtils.getIpAddr();
        IPGeolocation data = null;//AddressUtils.getAddresses(ip);
        String addresses = "未知地点";
        String ips = "未知服务商";
        if(data != null && "success".equals(data.getStatus())){
            addresses = data.getCountry()+data.getRegionName()+data.getCity();
            ips = data.getIsp();
        }
        SysLog sysLog = new SysLog(null,
                userName,//登录用户名
                operation,
                classMethodName,
                params,
                millisecond,
                ip,
                addresses,
                ips,
                new Date());
        //3.持久化日志信息
        logService.saveLogInfo(sysLog);
    }

    private String getOperation(Method targetMethod) {
        RequiredLog rLog = targetMethod.getAnnotation(RequiredLog.class);
        return rLog.value();
    }

    private String getTargetMethodName(Method targetMethod) {
        return targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName();
    }

    private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        MethodSignature s = (MethodSignature) joinPoint.getSignature();//方法签名
        Method targetMethod = targetClass.getMethod(s.getName(), s.getParameterTypes());
        return targetMethod;
    }
}








