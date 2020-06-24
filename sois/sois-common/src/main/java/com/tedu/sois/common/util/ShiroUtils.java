package com.tedu.sois.common.util;

import com.tedu.sois.common.pojo.sys.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {
   public static String getUsername() {
	   return getUser().getUserName();
   }
   /**获取登录用户*/
   public static SysUser getUser() {
	   return (SysUser) SecurityUtils.getSubject().getPrincipal();
   }
}
