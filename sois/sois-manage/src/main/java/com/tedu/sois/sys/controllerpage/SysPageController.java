package com.tedu.sois.sys.controllerpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回管理员相应页面
 * @author LYS
 */
@Controller
@RequestMapping("sys/")
public class SysPageController {

    /**
     * 返回 用户管理 界面
     * @param model
     * @return
     */
    @RequestMapping("doGetUserListInfoPage")
    public String doGetUserListInfoPage(Model model) {
        return "sys/user_list" ;
    }

    /**
     * 返回 角色管理 界面
     * @param model
     * @return
     */
    @RequestMapping("doGetRoleListInfoPage")
    public String doGetRoleListInfoPage(Model model) {
        return "sys/role_list" ;
    }

    /**
     * 返回 部门管理 界面
     * @param model
     * @return
     */
    @RequestMapping("doGetDeptListInfoPage")
    public String doGetDeptListInfoPage(Model model) {
        return "sys/dept_list" ;
    }

    /**
     * 返回 菜单管理 界面
     * @param model
     * @return
     */
    @RequestMapping("doGetMenuListInfoPage")
    public String doGetMenuListInfoPage(Model model) {
        return "sys/menu_list" ;
    }

    /**
     * 返回 日志管理 界面
     * @param model
     * @return
     */
    @RequestMapping("doGetLogListInfoPage")
    public String doGetLogListInfoPage(Model model) {
        return "sys/log_list" ;
    }

}
