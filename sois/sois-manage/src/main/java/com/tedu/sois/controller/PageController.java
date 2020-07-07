package com.tedu.sois.controller;

import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.sys.service.SysMenuService;
import com.tedu.sois.sys.vo.SysUserMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PageController {

    /**
     * 菜单业务接口
     */
    @Autowired
    private SysMenuService sysMenuService;
    
    /**
     * 返回主页
     *
     * @return
     */
    @RequestMapping("")
    public String goToIndex(Model model) {
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            String avatar = user.getAvatar();
            if (avatar == null || "".equals(avatar)){
                user.setAvatar("dist/layuiadmin/img/defualt.png");
            }else {
                user.setAvatar(avatar+"?"+Math.random());
            }
            model.addAttribute("userData", user);
            List<SysUserMenuVo> userMenus = sysMenuService.findMenusByUserId(user.getUserId());
            model.addAttribute("userMenus", userMenus);
        } else {
            user = new SysUser();
            user.setUserName("请登录");
            user.setAvatar("dist/layuiadmin/img/defualt.png");
            model.addAttribute("userData", user);
            List<SysUserMenuVo> userMenus = sysMenuService.findMenusByUserId(7L);
            model.addAttribute("userMenus", userMenus);
        }
        return "index";
    }


    @RequestMapping("doLoginUI")
    public String doLoginUI(Model model) {
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            model.addAttribute("userData", user);
            return "error_index";
        } else {
            user = new SysUser();
            user.setUserName("请登录");
            model.addAttribute("userData", user);
        }
        return "login";
    }

    @RequestMapping("doErrorUI")
    public String doErrorUI() {
        return "error";
    }



}
