package com.tedu.sois.common.web;

import com.tedu.sois.common.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ControllerAdvice 描述的类表示一个全局异常处理类
 * @ExceptionHandler 描述的方法为一个异常处理方法
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice//==@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    //@ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new JsonResult(e);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException( ShiroException e) {
        JsonResult r = new JsonResult();
        r.setCode(1);
        if (e instanceof UnknownAccountException) {
			r.setCode(450);
            r.setMsg("账户不存在");
        } else if (e instanceof LockedAccountException) {
			r.setCode(451);
            r.setMsg("账户违规禁用");
        } else if (e instanceof IncorrectCredentialsException) {
			r.setCode(452);
            r.setMsg("账号或密码不正确");
        } else if (e instanceof AuthorizationException) {
			r.setCode(453);
            r.setMsg("没有此操作权限");
        } else {
			r.setCode(501);
            r.setMsg("系统维护中");
        }
        e.printStackTrace();
        return r;
    }

}





