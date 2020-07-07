package com.tedu.sois.sys.controller;

import com.tedu.sois.common.pojo.JWTToken;
import com.tedu.sois.common.util.IPUtils;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.util.TokenUtil;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("user/")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @param loginName    登录名
     * @param password     密码
     * @param isRememberMe 记住我
     * @param session      session备用
     * @return 登录结果
     */
    @PostMapping("doLogin")
    public JsonResult doLogin(
            @RequestParam("loginName") String loginName
            , @RequestParam("password") String password
            ,@RequestParam("verCode") String verCode
            , boolean isRememberMe
            , HttpSession session) {

        if("".equals(verCode) || verCode == null){
            return new JsonResult(-1,"验证码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);

        String jwtToken = TokenUtil.createJWT(
                "jwt" + System.currentTimeMillis()
                , "{username:" + token.getUsername() + ",time:" + new Date() + "}"
                , TokenUtil.EFFECTIVE_TIME);
        token.setRememberMe(isRememberMe);
        //提交给securityManager
        subject.login(token);

        //登录成功后更新登录时间和ip
        SysUser data = ShiroUtils.getUser();
        SysUser user = new SysUser();
        user.setUserId(data.getUserId());
        user.setLoginDate(new Date());
        user.setLoginIp(IPUtils.getIpAddr());
        sysUserService.modifySingleUserInfoBySingle(user);

        return new JsonResult("登录成功", new JWTToken(jwtToken, data));
    }


    /**
     * 获取用户信息,如果不是登录状态登录就为空
     *
     * @return 用户信息
     */
    @RequestMapping("doGetLoginUser")
    public JsonResult doGetLoginUser() {
        return new JsonResult(ShiroUtils.getUser());
    }


    /**
     * 新增用户信息
     * @param entity
     * @param roleIds
     * @return
     */
    @PostMapping("doSaveUserInfo")
    public JsonResult doSaveUserInfo(SysUser entity, Integer[] roleIds) {
        sysUserService.saveSysUser(entity, roleIds);
        return new JsonResult("保存成功");
    }


    /**
     * 根据用户ID删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping("doRemoveSysUserInfoById")
    public JsonResult doRemoveSysUserInfoById(Long userId){
        sysUserService.removeSysUserInfoById(userId);
        return new JsonResult("删除用户成功");
    }

    /**
     * 修改密码
     *
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @param rePassword  验证新密码
     * @return 修改结果
     */
    @PostMapping("doModifyUserPassword")
    public JsonResult doModifyUserPassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("rePassword") String rePassword) {
        sysUserService.modifyUserPassword(oldPassword, newPassword, rePassword);
        return new JsonResult("修改密码成功");
    }

    /**
     * 修改账号个人信息
     * @param entity
     * @return
     */
    @PostMapping("doModifySingleUserInfoBySingle")
    public JsonResult doModifySingleUserInfoBySingle(SysUser entity) {
        sysUserService.modifySingleUserInfoBySingle(entity);
        return new JsonResult("修改信息成功");
    }

    /**
     * 更新用户自身信息以及用户对应的角色关系数据
     *
     * @param entity
     * @param roleIds
     * @return
     */
    @PostMapping("doModifySingleUserInfo")
    public JsonResult doModifySingleUserInfo(SysUser entity, Integer[] roleIds) {
        sysUserService.modifySysUserInfo(entity, roleIds);
        return new JsonResult("修改信息成功");
    }


    /**
     * 禁用启用业务方法
     *
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("doModifyStatusById")
    public JsonResult doModifyStatusById(Long userId, Integer status) {
        String modifiedUser = ShiroUtils.getUsername();
        sysUserService.modifyStatusById(userId, status, modifiedUser);
        return new JsonResult("修改成功");
    }


    /**
     * 基于用户id查询用户以及用户对应部门,角色相关信息
     *
     * @param userId
     * @return
     */
    @GetMapping("doFindUserInfoAndRoleIdsById")
    public JsonResult doFindUserInfoAndRoleIdsById(Long userId) {
        return new JsonResult(sysUserService.findUserDeptVoById(userId));
    }

    /**
     * 根据用户名分页查询
     * @param userName 用户名
     * @param page 页码
     * @param limit 查询数量
     * @return
     */
    @PostMapping("doFindUserDeptByUsername")
    public JsonResult doFindUserDeptByUsername(String userName, Integer page, Integer limit) {
        return sysUserService.findUserDeptByUserName(userName, page, limit);
    }


}
