package com.tedu.sois.sys;

import com.tedu.sois.sys.dao.SysUserDao;
import com.tedu.sois.sys.service.SysMenuService;
import com.tedu.sois.sys.vo.SysUserMenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class SysUserLoginTestCase {

    @Autowired
    org.apache.shiro.mgt.SecurityManager securityManager;

    //@Test
    public void login(){
        //set认证器
        SecurityUtils.setSecurityManager(securityManager);
        //subject发起认证,获取subject
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken("735622418@qq.com","30520564");

        //认证失败会抛出异常  密码：CredentialsException   账户：UnknownAccountException
        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //当前subject是否认证通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
    }

    @Autowired
    private SysUserDao sysUserDao;

    //@Test
    public void modifyPassword(){
        //3.对新密码进行加密
        String salt = UUID.randomUUID().toString();
        SimpleHash sh = new SimpleHash("MD5", "gyzx123", salt, 5);

        //4.将新密码加密以后的结果更新到数据库
        int rows = sysUserDao.updatePassword(sh.toHex(), salt, 2L, "admin");
    }

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void findSysMenusByUserId(){
        List<SysUserMenuVo> menus = sysMenuService.findMenusByUserId(2L);
        for (SysUserMenuVo sysUserMenuVo: menus) {
            System.err.println(sysUserMenuVo);
        }
    }
}
